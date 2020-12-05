package com.shneider.hrautomation.data.interview

import com.shneider.hrautomation.data.application.Status
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "Interviews")
data class InterviewDTO(
        @Id
        val id: ObjectId = ObjectId.get(),
        val createdDate: LocalDateTime = LocalDateTime.now(),
        val modifiedDate: LocalDateTime = LocalDateTime.now(),
        var candidateId: String,
        var positionId: String,
        var status: Status = Status.INTERVIEW_UPCOMING,
        var feedback: Feedback = Feedback.NONE,
        var interviewerId: ObjectId
)

