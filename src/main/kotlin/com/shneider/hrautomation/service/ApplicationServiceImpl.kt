package com.shneider.hrautomation.service

import com.shneider.hrautomation.data.ApplicationDTO
import com.shneider.hrautomation.data.ApplicationRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class ApplicationServiceImpl : ApplicationService {
    private var applicationRepository: ApplicationRepository? = null

    @Autowired
    fun setApplicationRepository(
        applicationRepository: ApplicationRepository
    ) {
        this.applicationRepository = applicationRepository
    }

    override fun listAllApplications(): Iterable<ApplicationDTO> {
        return applicationRepository!!.findAll()
    }

    override fun getApplicationByCandidateId(id: ObjectId): Iterable<ApplicationDTO> {
        return applicationRepository!!.findAll().filter {
            it.candidateId == id
        }
    }

    override fun getApplicationById(id: String?): ApplicationDTO {
        return applicationRepository!!.findOneById(ObjectId(id))
    }

    override fun saveApplication(application: ApplicationDTO): ApplicationDTO {
        return applicationRepository!!.save(application)
    }
}
