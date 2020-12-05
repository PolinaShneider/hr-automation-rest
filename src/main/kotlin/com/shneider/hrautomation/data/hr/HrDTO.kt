package com.shneider.hrautomation.data.hr

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "Hr")
data class HrDTO (
        @Id
        val id: ObjectId = ObjectId.get(),
        val name: String,
        val username: String,
        val description: String,
        val createdDate: LocalDateTime = LocalDateTime.now(),
        val modifiedDate: LocalDateTime = LocalDateTime.now()
)