package com.shneider.hrautomation.data.candidate

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "Candidates")
data class CandidateDTO (
        @Id
        val id: ObjectId = ObjectId.get(),
        val name: String = "Anonymous User",
        val description: String = "",
        val createdDate: LocalDateTime = LocalDateTime.now(),
        val modifiedDate: LocalDateTime = LocalDateTime.now(),
        val worksInCompany: Boolean = false,
        val username: String,
        val applicationIds: List<Int> = emptyList()
)