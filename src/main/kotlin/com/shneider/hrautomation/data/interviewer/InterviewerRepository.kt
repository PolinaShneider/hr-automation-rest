package com.shneider.hrautomation.data.interviewer

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface InterviewerRepository : MongoRepository<InterviewerDTO, String> {
    fun findOneById(id: ObjectId): InterviewerDTO

    fun findByUsername(username: String): InterviewerDTO
}