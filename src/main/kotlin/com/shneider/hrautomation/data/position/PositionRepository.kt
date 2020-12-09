package com.shneider.hrautomation.data.position

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface PositionRepository : MongoRepository<PositionDTO, String> {
    fun findAllByOpenTrue(): List<PositionDTO>

    fun findOneById(id: ObjectId): PositionDTO
}