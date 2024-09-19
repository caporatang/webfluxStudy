package com.example.mongochat.repository;

import com.example.mongochat.entity.ChatDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * packageName : com.example.mongochat.repository
 * fileName : ChatMongoRepository
 * author : taeil
 * date : 2024. 9. 19.
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2024. 9. 19.        taeil                   최초생성
 */
public interface ChatMongoRepository extends ReactiveMongoRepository<ChatDocument, ObjectId> {



}