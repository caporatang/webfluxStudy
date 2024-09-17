# MongoDB Document
MongoCollection에 query를 실행해서 bson의 Document를 반환하고, bson의 Document는 Map<String, Object> 를 구현하고 내부에 LinkedHashMap을 저장하여 Map 메서드를 overrie한다.  

## Document 예제
collection에서 findAll query를 실행하여 결과를 subscribe 후 onNext로 출력한다. 모든 결과를 찾은 후 onComplete 이벤트로 종료한다.
````java
public class getDocumentExample {
    var connection = new ConnectionString("mongodb://localhost:27017/capo");
    var settings = MongoClientSettings.builder()
            .applyConnectionString(connection);
    
  try(MongoClient mongoClient = MongoClients.create(settings)) {
        var database = mongoClient.getDatabase("capo");
        var collection = database.getCollection("person");
        
        collection.find()
                .subscribe(new SimpleSubscriber<>());
        
        Thread.sleep(1000);
    }
}
````

### Java 클래스를 Bson 타입으로 변경하는 방법
mongoDB Bson이 binary로 저장될때 암호화되어 저장되는데, 첫 줄은 전체 document의 크기를 가리키게 된다. 데이터 타입, 필드명, 길이(데이터 타입에 따라 옵셔널) 값으로 구성되어 있다.  
````text
{"hello": "world"} →
\x16\x00\x00\x00           // total document size
\x02                       // 0x02 = type String
hello\x00                  // field name
\x06\x00\x00\x00world\x00  // field value
\x00                       // 0x00 = type EOO ('end of object')
````
그래서 Bson은 다음과 같은 타입들로 java에서 사용할 수 있게 변환한다. 많은 데이터가 더 있지만 자주 사용하는 자료형이다.

| BSON Type  | Number | Java Type                                           |
|------------|-------|-----------------------------------------------------|
| Double     | 1     | Double                                              |  
| String     | 2     | Character, String, Enum                             | 
| Object     | 3     |                                                     | 
| Array      | 4     |                                                     | 
| Binary Data | 5     | Binary (bson에서 제공), byte[]                          | 
| ObjectId   | 7     | ObjectId (bson에서 제공)                                | 
| Boolean    | 8     | Boolean, AtomicBoolean                              | 
| Date       | 9     | Datte, Instant, LocalDate, LocalDateTime, LocalTime | 
| Null       | 10    |                                                     | 

이러한 타입으로 변환해주는 클래스가 Codec 클래스다. bson 라이브러리는 Codec을 제공하고, Codec을 통해서 특정 Java type이 주어졌을 때 어떻게 encode, decode 해야할지를 결정 후 변환한다.
````java
public interface Codec<T> extends Encoder<T>, Decoder<T> {
}
````
#### Default Codec
MongoClientSettings에서 Default codec을 제공한다 Java 자체 클래스와 관련된 Codec들을 이용해서 codec을 제공한다. 그리고 반대로 Bson 관련 codec도 같이 지원한다.
- Java 관련 codec
    - iterableCodecProvider : Iterable 클래스 지원
    - MapCodecProvider : Map 클래스 지원
    - ValueCodecProvider : Java에서 제공하는 클래스 지원
    - Jsr310CodecProvider : Instant, LocalDate, LocalDateTime 등 Date, Time 관련 클래스 지원
    - EnumCodecProvider : Enum 지원
    - Jep395RecordCodecProvider : Record 지원
- Bson 관련 codec
  - BsonValueCodecProvider : Bson 타입들을 Java로 1대1 매핑한 클래스를 지원
  - DBRefCodecProvider : DBRef 지원
  - DocumentCodecProvider : Document 지원 
  - GeoJsonCodecProvider : Geometry, LineString, MultiPoint, Point, Polygon 등의 geojson 지원 
  - GridFSFileCodecProvider : GridFSFile 지원
  - JsonObjectCodecProvider : JsonObject 지원
````java
@Immutable
public final class MongoClientSettings {
    private static final CodecRegistry DEFAULT_CODEC_REGISTRY = CodecRegistries.fromProviders(Arrays.asList(new ValueCodecProvider(), 
            new BsonValueCodecProvider(), 
            new DBRefCodecProvider(),
            new DBObjectCodecProvider(),
            new DocumentCodecProvider(new DocumentToDBRefTransformer()), 
            new CollectionCodecProvider(new DocumentToDBRefTransformer()),
            new IterableCodecProvider(new DocumentToDBRefTransformer()),
            new MapCodecProvider(new DocumentToDBRefTransformer()), 
            new GeoJsonCodecProvider(), 
            new GridFSFileCodecProvider(), 
            new Jsr310CodecProvider(),
            new JsonObjectCodecProvider(), 
            new BsonCodecProvider(), 
            new EnumCodecProvider(), 
            new ExpressionCodecProvider(),
            new Jep395RecordCodecProvider()));
````
MongoDB에 저장하고 데이터를 가져올때마다 codec을 구현하는건 번거롭고 힘들다. 그래서 추가된게 PojoCodec이다. PojoCodec은 주어진 POJO 객체를 bson으로, bson을 POJO로 자동 변환하는 Codec이다. PojoCodec은 기본으로 추가되지 않으므로 별도로 추가해야 한다.

````java
import ch.qos.logback.core.sift.Discriminator;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;

abstract class PojoCodec<T> implements Codec<T> {
    abstract ClassModel<T> getClassModel();

    abstract DiscriminatorLookup getDiscriminatorLookup();
}
````