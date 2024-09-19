package com.example.mongochat.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * packageName : com.example.mongochat.entity
 * fileName : ChatDocument
 * author : taeil
 * date : 2024. 9. 19.
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2024. 9. 19.        taeil                   최초생성
 */
@Data
@Document(collection = "chat")
public class ChatDocument {
    @Id
    private final ObjectId id;
    private final String from;
    private final String to;
    private final String message;

    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedDate
    private LocalDateTime updateAt;

    public ChatDocument(String from, String to, String message) {
        this(null, from, to, message, null, null);
    }

    @PersistenceConstructor
    public ChatDocument(ObjectId id, String from, String to, String message, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.message = message;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public ChatDocument withId(ObjectId id) {
        return new ChatDocument(id, this.from, this.to, this.message, this.createAt, this.updateAt);
    }
}