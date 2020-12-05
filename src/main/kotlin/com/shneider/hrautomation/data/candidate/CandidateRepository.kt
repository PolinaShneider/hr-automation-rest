package com.shneider.hrautomation.data.candidate

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface CandidateRepository : MongoRepository<CandidateDTO, String> {
    fun findOneById(id: ObjectId): CandidateDTO

    fun deleteByUsername(username: String)

    fun findOneByUsername(username: String): CandidateDTO
}