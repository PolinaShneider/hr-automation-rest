package com.shneider.hrautomation.data.application

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface ApplicationRepository : MongoRepository<ApplicationDTO, String> {
    fun findOneById(id: ObjectId): ApplicationDTO
    fun findAllByCandidateId(candidateId: ObjectId): List<ApplicationDTO>
    fun findAllByRotationTrue(): List<ApplicationDTO>
    fun findByCandidateIdAndPositionId(candidateId: ObjectId, positionId: ObjectId): ApplicationDTO
}