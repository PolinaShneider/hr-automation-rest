package com.shneider.hrautomation.data.hr

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface HrRepository : MongoRepository<HrDTO, String> {
    fun findOneById(id: ObjectId): HrDTO

    fun findByUsername(username: String): HrDTO
}