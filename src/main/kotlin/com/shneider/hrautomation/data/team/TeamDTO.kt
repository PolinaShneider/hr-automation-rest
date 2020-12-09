package com.shneider.hrautomation.data.team

import org.bson.types.ObjectId
import org.hibernate.validator.constraints.UniqueElements
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "Teams")
data class TeamDTO(
        @Id
        val id: ObjectId = ObjectId.get(),
        val createdDate: LocalDateTime = LocalDateTime.now(),
        val modifiedDate: LocalDateTime = LocalDateTime.now(),
        val alias: String,
        var title: String
)
