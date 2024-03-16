package com.example.springdatareactive.r2dbc.mysql;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import dev.miku.r2dbc.mysql.MySqlResult;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.springdatareactive.r2dbc
 * fileName : R2dbcConnectionTxExample
 * author : taeil
 * date : 3/16/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 3/16/24        taeil                   최초생성
 */
public class R2dbcConnectionTxExample {
    // Transaction 실행
    // connection의 beginTransaction, commitTransaction으로 transation 시작과 commit을 수행한다.
    // onErrorResume으로 exception이 발생하면 rollbackTransaction을 수행한다..!

    public static void main(String[] args) {
        var config = MySqlConnectionConfiguration.builder()
                .host("localhost")
                .port(3306)
                .username("root")
                .password("1234")
                .database("DBTest")
                .build();
        var connectionFactory = MySqlConnectionFactory.from(config);

        var tableSql = "Create TABLE IF NOT EXISTS person" +
                "(id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255)," +
                "gender VARCHAR(10)," +
                "age INT)";
        var insertSql = "INSERT INTO person(name, age, gender)" +
                "VALUES (?name, ?age, ?gender)";
        var selectSql = "SELECT * FROM person";
        
        
        connectionFactory.create().flatMapMany(conn -> {
            Flux<MySqlResult> createTable = (Flux<MySqlResult>) conn.createStatement(tableSql).execute();
        var insertPerson = conn.createStatement(insertSql)
                .bind("name", "taewoo")
                .bind("age", 20)
                .bind("gender", "M")
                .execute();
        var selectPeople = conn.createStatement(selectSql)
                .execute();
        return createTable
                .then(conn.beginTransaction())// 트랜잭션 시작
                .thenMany(insertPerson)
                .thenMany(selectPeople)
                .then(conn.commitTransaction())// 트랜잭션을 commit !
                .onErrorResume(err -> {
                    return conn.rollbackTransaction();
                });
        }).subscribe();
    }
}