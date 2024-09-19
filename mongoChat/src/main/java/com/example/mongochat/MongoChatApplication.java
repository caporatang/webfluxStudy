package com.example.mongochat;

import com.example.mongochat.entity.ChatDocument;
import com.example.mongochat.repository.ChatMongoRepository;
import com.mongodb.client.model.changestream.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@SpringBootApplication
@Slf4j
public class MongoChatApplication  {

    public static void main(String[] args) {
        SpringApplication.run(MongoChatApplication.class, args);
    }

}
