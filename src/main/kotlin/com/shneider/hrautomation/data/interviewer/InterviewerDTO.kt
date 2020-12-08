package com.shneider.hrautomation.data.interviewer

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "Interviewers")
data class InterviewerDTO (
        @Id
        val id: ObjectId = ObjectId.get(),
        val name: String = "Anonymous User",
        val username: String,
        val description: String = "",
        val createdDate: LocalDateTime = LocalDateTime.now(),
        val modifiedDate: LocalDateTime = LocalDateTime.now(),
        val interviews: List<String> = emptyList()
)