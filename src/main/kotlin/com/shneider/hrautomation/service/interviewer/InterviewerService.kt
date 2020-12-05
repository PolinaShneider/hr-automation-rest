package com.shneider.hrautomation.service.interviewer

import com.shneider.hrautomation.data.interview.Feedback
import com.shneider.hrautomation.data.interviewer.Interviewer
import com.shneider.hrautomation.data.interview.Interview
import com.shneider.hrautomation.request.InterviewerRequest


interface InterviewerService {
    fun getAvailableInterviewer(): Interviewer

    fun conductInterview(id: String, feedback: Feedback): Interview

    fun getInterviewer(id: String): Interviewer

    fun updateInfo(username: String, interviewerRequest: InterviewerRequest)
}
