package com.example.springdatareactive.r2dbc;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import dev.miku.r2dbc.mysql.MySqlResult;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.springdatareactive.r2dbc
 * fileName : R2dbcConnectionExample
 * author : taeil
 * date : 3/16/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 3/16/24        taeil                   최초생성
 */
@Slf4j
public class R2dbcConnectionExample {
    public static void main(String[] args) {

        // MysqlConnectionConfiguration builder 를 이용하여 config 생성
        var config = MySqlConnectionConfiguration.builder()
                .host("localhost")
                .port(3306)
                .username("root")
                .password("1234")
                .database("DBTest")
                .build();
        // MySqlConnection Factory의 from static 메서드를 이용하여 connectionFactory 생성
        var connectionFactory = MySqlConnectionFactory.from(config);

        // Sql 준비
        // person 테이블과 각 필드 생성
        // 새로운 row 데이터 생성
        // parameter 처리 ?를 이용
        // person 테이블 조회
        var tableSql = "Create TABLE IF NOT EXISTS person" +
                "(id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255)," +
                "gender VARCHAR(10)," +
                "age INT)";
        var insertSql = "INSERT INTO person(name, age, gender)" +
                "VALUES (?name, ?age, ?gender)";
        var selectSql = "SELECT * FROM person";


        // connectionFactory의 create를 통해서 connection에 접근
        // connection의 createStatement를 통해서 sql 준비
        // thenMany chaining을 통해서 순차적으로 실행하고 selectPeople의 결과를 아래로 전달한다.
        // result의 map으로 row에 접근하고 Person으로 변환
        connectionFactory.create().flatMapMany(conn -> {
            Flux<MySqlResult> createTable = (Flux<MySqlResult>) conn.createStatement(tableSql).execute();
            Flux<MySqlResult> insertPerson = (Flux<MySqlResult>) conn.createStatement(insertSql)
                    .bind("name", "taeil")
                    .bind("age", 29)
                    .bind("gender", "M")
                    .add()
                    .bind("name", "taeil2")
                    .bind("age", 30)
                    .bind("gender", "W")
                    .execute();
              Flux<MySqlResult> selectPeople = (Flux<MySqlResult>) conn.createStatement(selectSql).execute();

              return createTable.thenMany(insertPerson)
                      .thenMany(selectPeople);
        }).flatMap(result -> {
            return result.map((row, rowMetadata) -> { // 모든 person의 row를 조회 및 처리
                Long id = row.get("id", Long.class);
                String name = row.get("name", String.class);
                Integer age = row.get("age", Integer.class);
                String gender = row.get("gender", String.class);
                return new Person(id, name, age, gender);
            });
        }).subscribe(person -> log.info("person : {}", person));

        // SQL 쿼리를 명시적으로 전달하기 떄문에 개발할때 불편해.. 쿼리 재사용도 못해..
        // 반환된 결과도 알아서 개발자가 해야되고, 관리할려면 mapper도 만들어야하고 확장성도 매우매우매우매우 불편해
        // ->  MVC1 DB 연결이라고 생각하면되지, 편하게 하기 전에 왜 이렇게 편하게 쓸수있는지는 알아야하니까 작성
    }
}