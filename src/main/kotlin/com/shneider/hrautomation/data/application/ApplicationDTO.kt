package com.shneider.hrautomation.data.application

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "Applications")
data class ApplicationDTO(
        @Id
        var id: ObjectId = ObjectId.get(),
        val createdDate: LocalDateTime = LocalDateTime.now(),
        val modifiedDate: LocalDateTime = LocalDateTime.now(),
        val candidateId: ObjectId,
        val positionId: ObjectId,
        val status: Status = Status.PENDING,
        val rotation: Boolean = false
)