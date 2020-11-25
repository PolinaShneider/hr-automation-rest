package com.shneider.hrautomation.data

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface CandidateRepository : MongoRepository<CandidateDTO, String> {
    fun findOneById(id: ObjectId): CandidateDTO
    override fun deleteAll()
}