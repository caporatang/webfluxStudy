# MongoDB Collection

## driver
MongoDB사에서 공식적인 2가지 Sync Driver, reactive streams driver 를 제공한다
- Sync Driver 
  - 동기적으로 동작하는 어플리케이션을 위한 MongoDB 드라이버
  - 클라이언트가 요청을 보내면 응답이 돌아오기전까지 스레드가 blocking
  - 메서드가 응답 객체를 바로 반환하기 때문에 직관적이며 쉽게 작성할 수 있다
  - 스레드 동시성 문제로 많은 요청을 처리하기 힘들다

## Mongo Reactive Streams Driver
- 비동기적으로 동작하는 어플리케이션을 위한 MongoDB 드라이버
- 클라이언트가 요청을 보내면 스레드는 non-blocking
- 모든 응답이 Publisher를 이용해서 전달되기 때문에 처리하기 어렵다
- Spring reactive stack과 함께 사용되어 높은 성능과 안전성을 제공한다.

Client 객체를 생성해서 DB에 접근하고 collection을 조회할 수 있다.
````java
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MongoDBGetCollectionExample {
  // 연결 정보   
  var connection = new ConnectionString("mongodb://localhost:27017/capo");
  
  // 세팅에 생성한 연결정보 전달
  var settings = MongoClientSettings.builder()
          .applyConnectionString(connection);
    
  // 클라이언트 생성
  try(MongoClient mongoClient = MongoClients.create(settings)) {
    
    // db 접근
    var database = mongoClient.getDatabase("capo");
    log.info("database : {}", database.getName());
    
    // Collection 접근
    var collection = database.getCollection("person");
    log.info("collection : {}", collection.getNamespace().getCollectionName());
  }
}
````

## MongoCollection
위 코드로 DB에 접근해서 반환받은 Colleciton은 다양한 연산을 제공한다.

### count
  - ClientSession을 통해서 multidocument transaction을 제공한다
  - Bson 구현체 (BsonDocument 등)로 filter를 제공
  - CountOptions로 hint, limit, skip, maxTime, collation 등의 정보 제공
````java
Publisher<Long> countDocuments(ClientSession clientSession, Bson filter, CountOptions options);

public class CountOptions { 
    private Bson hint;
    private String hintString;
    private int limit;
    private int skip;
    private long maxTimeMS;
    private Collation collation;
    private BsonValue comment;
}
````

### find
- Filters helper 클래스를 통해서 filter 설정 가능
  - eq, ne, gt, gte, lt, lte, in, nin, and, or, not, nor, exists, type, mod, regex, text 등의 기본 연산자 제공
  - geoWithin, geoWithinBox 등의 geo 연산자도 같이 제공

````java
import com.mongodb.lang.Nullable;
import com.mongodb.reactivestreams.client.ClientSession;
import com.mongodb.reactivestreams.client.FindPublisher;
import org.bson.conversions.Bson;

FindPublisher<TDcument> find(ClientSession clientSession, Bson filter);

public final class Filters {
  private Filters() {
  }

  public static <TItem> Bson eq(@Nullable final TItem value) {...}
  public static <TItem> Bson ne(final String fieldName, @Nullable final TItem value) {...}
  public static <TItem> Bson gt(final String fieldName, final TItem value) {...}
  public static <TItem> Bson in(final String fieldName, final Iterable<TItem> values) {...}
}
````

### aggregate
aggregate는 pipeline을 생성하고 mongo shard 전체에 대해서 필터, 집계 그룹 등의 연산을 수행할 수 있다. 정말 많은 연산자가 제공되니 따로 사용해 볼 필요가 있다.

````java
AggregatePublisher<TDocument> aggregate(ClientSession clientSession, List<? extends Bson> pipeline);

public final class Aggregates {
    public static Bson addfields(final List<Field<?>> fields){ ... }
    public static Bson set(final List<Field<?>> fields){ ... }
    public static Bson count(final String field){ ... }
    public static Bson mathch(final Bson filter){ ... }
    public static Bson project(final Bson projection){ ... }
    public static Bson sort(final Bson sort){ ... }
}
````


````java
import com.mongodb.reactivestreams.client.AggregatePublisher;
import com.mongodb.reactivestreams.client.ClientSession;
import dev.miku.r2dbc.mysql.client.Client;
db.orders.aggregate([
    {
      // 상태가 A와 일치하는 데이터
      $match:{ status: "A" }
    },
      {
          $group:{
              _id:"$customer_id",
              total:{$sum:"$amount"}
          }
      }
    ])
````

### watch
Aggregates helper 클래스를 통해서 aggregate pipeline을 제공한다.  
aggregate pipeline을 사용해서 Collection Document에 변화가 생기면, publisher 파이프라인을 타고 전달된다.

- aggregate pipeline의 모든 기능을 사용할 수 있는게 아니라 특정 기능만 사용할 수 있다.
  - addfields, match, project, replaceRoot, replaceWith, redact, set, unset  

- changeStreamPublisher를 반환하고 해당 Publisher를 subscribe해서 사용할 수 있다.
  - ChangeStreamDocument를 onNext로 전달한다.
  - subscribe를 일시정지하거나 재개하거나 변경사항이 발생한 Document의 id를 가져온다던지 다양한 기능을 제공한다.
   

````java
ChangeStreamPublisher<Document> watch(ClientSession var1, List<? extends Bson> var2);

public interface ChangeStreamPublisher<TResult> extends Publisher<ChangeStreamDocument<TResult>> { ... }

public final class ChangeStreamDocument<TDocument> {
  @BsonId
  private final BsonDocument resumeToken;
  private final BsonDocument namespaceDocument;
  private final BsonDocument destinationNamespaceDocument;
  private final TDocument fullDocument;
  private final TDocument fullDocumentBeforeChange;
  private final BsonDocument documentKey;
  private final BsonTimestamp clusterTime;
  @BsonProperty("operationType")
  private final String operationTypeString;
  @BsonIgnore
  private final OperationType operationType;
  private final UpdateDescription updateDescription;
  private final BsonInt64 txnNumber;
  private final BsonDocument lsid;
  private final BsonDateTime wallTime;
  @BsonExtraElements
  private final BsonDocument extraElements;
````

### bulkWrite
- Delete, Insert, Replace, Update 등을 모아서 한 번에 실행하는 operation이다.
  - DeleteManyModel : 조건을 만족하는 document를 모두 삭제한다.
  - DeleteOneModel : 조건을 만족하는 documentfmf 최대 1개만 삭제한다.
  - InsertOneModel : 하나의 document를 추가한다.
  - ReplaceOneModel : 조건을 만족하는 document를 최대 1개만 대체한다
  - UpdateManyModel : 조건을 만족하는 document를 모두 수정한다.
  - UpdateOneMode : 조건을 만족하는 document를 최대 1개만 수정한다.

````java
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.WriteModel;
import com.mongodb.reactivestreams.client.ClientSession;

Publisher<BulkWriteResult> bulkWrite(ClientSession clientSession, List<? extends WriteModel<? extends TDocument>> requests, BulkWriteOptions options);

public final class BulkWriteOptions {
    // requests 가 list 형태로 주어졌을 때, 순차적으로 진행할 것인가
    private boolean ordered = true;
    // insert or update 할 때 validation은 Pass
    private boolean bypassDocumentValidation;
    private BsonValue comment;
    private Bson variables;
}
````
### insert 
- 하나 혹은 여러 document를 추가하는 operation
- InsertOneOptions 혹은 InsertManyOptions를 통해서 validation 우회 여부를 결정하고 InsertManyOptions라면 insert의 순서를 보장할지 결정한다
- InsertOneResult, InsertManyResult는 wasAcknowledged와 getInsertedlds 메서드를 통해서 write이 성공했는지, write된 id를 제공한다.

````java
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.result.InsertOneResult;
import org.bson.BsonValue;

Publisher<InsertOneResult> insertOne(ClientSession clientSession, TDocument document, InsertOneOptions options);

Publisher<InsertManyResult> insertMany(ClientSession clientSession, List<? extends TDocument> document, InsertManyOptions);

public final class InsertOneOptions {
  private Boolean bypassDocumentValidation;
  private BsonValue comment;
}
public final class InsertManyOptions {
    private boolean ordered = true;
    private Boolean bypassDocumentValidation;
    private BsonValue comment;
}
````

### update
- 하나 혹은 여러 document를 수정하는 operation
- Filters helper 클래스를 통해서 filter 설정 가능
- Updates helper 클래스를 통해 update 설정 가능
- UpdateOptions를 통해서 update, hint, collation, variables등을 제공한다.
````java
Publisher<UpdateResult> updateOne(ClientSession var1, Bson var2, List<? extends Bson> var3, UpdateOptions var4);
Publisher<UpdateResult> updateMany(Bson var1, List<? extends Bson> var2, UpdateOptions var3);

public class UpdateOptions {
    // 업데이트할 대상이 없는 경우 어떻게 할 것인가 
    private boolean upsert;
    
    private Boolean bypassDocumentValidation;
    private Collation collation;
    private List<? extends Bson> arrayFilters;
    private Bson hint;
    private String hintString;
    private BsonValue comment;
    private Bson variables;
}
````

### atomic
- findOneAndDelete, findOneAndReplace, findOneAndUpdate 등 find와 write를 묶어서 atomic한 operation을 제공한다.
````java 
Publisher<TDocument> findOneAndReplace(ClientSession var1, Bson var2, TDocument var3, FindOneAndReplaceOptions var4);
Publisher<TDocument> findOneAndUpdate(ClientSession var1, Bson var2, List<? extends Bson> var3, FindOneAndUpdateOptions var4);
````
### index
- Collection에서 특정 필드들에 대한 index 생성, 조회, 삭제 가능
- Indexes helper 클래스를 통해서 다양한 index 제공
- IndexModel과 IndexOptions를 통해서 어떤 필드들에 대해서 어떻게 Index를 적용할 것인지 설정할 수 있다.
- ascending, descending 
- geo2dshere, geo2d, heoHaystack
- text, hashed
- compoundIndex

````java
import com.mongodb.client.model.Collation;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.session.ClientSession;

Publisher<String> createIndex(ClientSession var1, Bson var2, IndexOptions var3);

Publisher<String> createIndexes(ClientSession var1, List<IndexModel> var2, CreateIndexOptions var3);

Publisher<Void> dropIndex(ClientSession clientSession, Bson keys, DropIndexOptions dropIndexOptions);

public class IndexModel {
  private final Bson keys;
  private final IndexOptions options;
}

public class IndexOptions {
  // index 생성을 background에서 진행할지 여부
  private boolean background;
  // unique index 생성 여부
  private boolean unique;
  // index 이름 설정
  private String name;
  // 특별한 조건을 충족한 경우에만 index를 설정하고 싶은 경우
  private Bson partialFilterExpression;
  private Collation collation;
}

````
