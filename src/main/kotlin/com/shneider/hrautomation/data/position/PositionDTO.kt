package com.shneider.hrautomation.data.position

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("Position")
data class PositionDTO(
        @Id
        val id: ObjectId = ObjectId.get(),
        val createdDate: LocalDateTime = LocalDateTime.now(),
        val modifiedDate: LocalDateTime = LocalDateTime.now(),
        var teamId: String,
        var title: String,
        var requirements: String,
        var isOpened: Boolean = true
)
