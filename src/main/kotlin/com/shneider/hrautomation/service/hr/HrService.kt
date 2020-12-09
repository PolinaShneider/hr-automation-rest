package com.shneider.hrautomation.service.hr

import com.shneider.hrautomation.data.application.Application
import com.shneider.hrautomation.data.application.Status
import com.shneider.hrautomation.data.hr.Hr
import com.shneider.hrautomation.data.interview.Feedback
import com.shneider.hrautomation.data.interview.Interview
import com.shneider.hrautomation.data.position.Position
import com.shneider.hrautomation.request.HrRequest
import com.shneider.hrautomation.request.PositionRequest
import com.shneider.hrautomation.request.StatusRequest


interface HrService {
    fun notifyMe(status: Status, applicationId: String, feedback: Feedback = Feedback.NONE)

    fun scheduleInterview(applicationId: String, request: StatusRequest): Interview

    fun updateApplication(applicationId: String, request: StatusRequest): Application

    fun createPosition(request: PositionRequest): Position

    fun updatePosition(positionId: String, request: PositionRequest)

    fun updateInfo(username: String, hrRequest: HrRequest)

    fun getHrByUsername(username: String): Hr
}
