package com.shneider.hrautomation.service.application

import com.shneider.hrautomation.data.application.Application
import com.shneider.hrautomation.data.application.Status
import com.shneider.hrautomation.request.ApplicationRequest
import org.bson.types.ObjectId


interface ApplicationService {
    fun listAllApplications(): List<Application>

    fun listRotationApplications(): List<Application>

    fun getApplicationById(id: String): Application

    fun getApplicationByCandidateId(id: String): List<Application>

    fun saveApplication(request: ApplicationRequest): Application

    fun updateApplicationStatus(applicationId: String, status: Status): Application

    fun postInterviewUpdate(candidateId: ObjectId, positionId: ObjectId, status: Status)

    fun linkInterview(applicationId: String, interviewId: String)
}
