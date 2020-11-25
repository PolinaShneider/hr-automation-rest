package com.shneider.hrautomation.data

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface ApplicationRepository : MongoRepository<ApplicationDTO, String> {
    fun findOneById(id: ObjectId): ApplicationDTO
    override fun deleteAll()
}