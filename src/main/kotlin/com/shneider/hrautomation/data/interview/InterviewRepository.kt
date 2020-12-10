package com.shneider.hrautomation.data.interview

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface InterviewRepository : MongoRepository<InterviewDTO, String> {
    fun findOneById(id: ObjectId): InterviewDTO

    fun findAllByInterviewerIdOrderByModifiedDateDesc(interviewerId: ObjectId): List<InterviewDTO>

    fun findAllByCandidateIdOrderByModifiedDateDesc(candidateId: String): List<InterviewDTO>
}