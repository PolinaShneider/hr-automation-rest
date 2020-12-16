package com.shneider.hrautomation.data.application

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface ApplicationRepository : MongoRepository<ApplicationDTO, String> {
    fun findOneById(id: ObjectId): ApplicationDTO
    fun findAllByCandidateIdOrderByModifiedDateDesc(candidateId: ObjectId): List<ApplicationDTO>
    fun findAllByRotationTrueOrderByModifiedDateDesc(): List<ApplicationDTO>
    fun findByCandidateIdAndPositionId(candidateId: ObjectId, positionId: ObjectId): ApplicationDTO
    fun findAllByRotationFalseOrderByModifiedDateDesc(): List<ApplicationDTO>
}