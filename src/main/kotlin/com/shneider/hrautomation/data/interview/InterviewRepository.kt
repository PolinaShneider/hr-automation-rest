package com.shneider.hrautomation.data.interview

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface InterviewRepository : MongoRepository<InterviewDTO, String> {
    fun findOneById(id: ObjectId): InterviewDTO

    fun findAllByInterviewerId(interviewerId: ObjectId): List<InterviewDTO>
}