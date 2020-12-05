package com.shneider.hrautomation.service.interview

import com.shneider.hrautomation.data.interview.Feedback
import com.shneider.hrautomation.data.interview.Interview
import com.shneider.hrautomation.request.InterviewRequest


interface InterviewService {
    fun schedule(request: InterviewRequest): Interview

    fun update(id: String, request: InterviewRequest): Interview

    fun getAllByInterviewerId(id: String): List<Interview>

    fun getInterview(id: String): Interview

    fun getInterviewFeedback(id: String): Feedback
}
