package com.shneider.hrautomation.service.hr

import com.shneider.hrautomation.data.application.Application
import com.shneider.hrautomation.data.interviewer.Interviewer
import com.shneider.hrautomation.data.application.Status
import com.shneider.hrautomation.data.interview.Feedback
import com.shneider.hrautomation.data.interview.Interview
import com.shneider.hrautomation.data.position.Position
import com.shneider.hrautomation.request.HrRequest
import com.shneider.hrautomation.request.InterviewRequest
import com.shneider.hrautomation.request.PositionRequest


interface HrService {
    fun notifyMe(status: Status, applicationId: String, feedback: Feedback = Feedback.NONE)

    fun scheduleInterview(applicationId: String): Interview

    fun updateApplication(applicationId: String, status: Status): Application

    fun createPosition(request: PositionRequest): Position

    fun updatePosition(positionId: String, request: PositionRequest)

    fun updateInfo(username: String, hrRequest: HrRequest)
}
