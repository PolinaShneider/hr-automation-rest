package com.shneider.hrautomation.data.interview

import com.shneider.hrautomation.data.application.Status
import org.springframework.data.mongodb.core.mapping.Document

class Interview(
        var candidateId: String,
        var positionId: String,
        var status: Status = Status.INTERVIEW_UPCOMING,
        var interviewerId: String,
        var feedback: Feedback = Feedback.NONE
) {
    fun conduct(feedback: Feedback) {
        this.feedback = feedback
        this.status = Status.INTERVIEW_PASSED
    }

    companion object {
        fun create(result: InterviewDTO): Interview {
            return Interview(
                    candidateId = result.candidateId,
                    positionId = result.positionId,
                    status = result.status,
                    feedback = result.feedback,
                    interviewerId = result.interviewerId.toString()
            )
        }
    }
}

