package com.example.springdatareactive.r2dbc.r2dbcEntityTemplate;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;

/**
 * packageName : com.example.springdatareactive.r2dbc.r2dbcEntityTemplate
 * fileName : R2dbcDatabaseClientExample
 * author : taeil
 * date : 3/16/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 3/16/24        taeil                   최초생성
 */
@Slf4j
public class R2dbcDatabaseClientExample {
    // sql을 실행하여 GenericExecuteSpec을 반환한다.
    // GenericExecuteSpec에 bind를 한 후 fetch를 호출하여 FetchSpec 반환
    // rowsUpdated를 호출하여 영향을 받은 row 수 조회
    // 혹은 all을 호출하여 결과 row 조회
    // -> mysql 예제 처럼 이렇게 해도 똑같이 개발자가 직접 매핑해야한다........................................

    public static void main(String[] args) {
        var config = MySqlConnectionConfiguration.builder()
                .host("localhost")
                .port(3306)
                .username("root")
                .password("1234")
                .database("DBTest")
                .build();
        var connectionFactory = MySqlConnectionFactory.from(config);

        var client = DatabaseClient.create(connectionFactory);

        var tableSql = "Create TABLE IF NOT EXISTS person" +
                "(id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255)," +
                "gender VARCHAR(10)," +
                "age INT)";
        var insertSql = "INSERT INTO person(name, age, gender)" +
                "VALUES (?name, ?age, ?gender)";
        var selectSql = "SELECT * FROM person";


        var createTableMono = client.sql(tableSql)
                .fetch()
                .rowsUpdated();

        var insertMono = client.sql(insertSql)
                .bind("name", "taeil")
                .bind("age", 29)
                .bind("gender", "M")
                .fetch()
                .rowsUpdated();

        var selectAllFlux = client.sql(selectSql)
                .fetch()
                .all();
                // all의 결과는 Map<Stirng, Object>

        createTableMono.then(insertMono)
                .thenMany(selectAllFlux)
                .doOnNext(result -> {
                    var id = result.get("id");
                    var name = result.get("name");
                    var age = result.get("age");
                    var gender = result.get("gender");
                    log.info("id: {}, name: {}, age: {}, gender: {}",
                            id, name, age, gender);
                })
                .subscribe();
    }
}