package com.shneider.hrautomation.data.interviewer

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("Interviewer")
data class InterviewerDTO (
        @Id
        val id: ObjectId = ObjectId.get(),
        val name: String,
        val description: String,
        val createdDate: LocalDateTime = LocalDateTime.now(),
        val modifiedDate: LocalDateTime = LocalDateTime.now(),
        val interviews: List<Int> = emptyList()
)