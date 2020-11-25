package com.shneider.hrautomation.service

import com.shneider.hrautomation.data.ApplicationDTO
import org.bson.types.ObjectId


interface ApplicationService {
    fun listAllApplications(): Iterable<ApplicationDTO>

    fun getApplicationById(id: String?): ApplicationDTO

    fun getApplicationByCandidateId(id: ObjectId): Iterable<ApplicationDTO>

    fun saveApplication(application: ApplicationDTO): ApplicationDTO
}
