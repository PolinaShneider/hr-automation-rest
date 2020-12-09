package com.shneider.hrautomation.service.application

import com.shneider.hrautomation.data.application.Application
import com.shneider.hrautomation.data.application.ApplicationDTO
import com.shneider.hrautomation.data.application.ApplicationRepository
import com.shneider.hrautomation.data.application.Status
import com.shneider.hrautomation.request.ApplicationRequest
import org.bson.types.ObjectId
import org.springframework.stereotype.Service


@Service
class ApplicationServiceImpl(
        private val applicationRepository: ApplicationRepository
) : ApplicationService {
    override fun postInterviewUpdate(candidateId: ObjectId, positionId: ObjectId, status: Status) {
        val dto = applicationRepository.findByCandidateIdAndPositionId(candidateId, positionId)
        applicationRepository.save(dto.copy(status = status))
    }

    override fun listRotationApplications(): List<Application> {
        return applicationRepository.findAllByRotationTrue().map {
            Application.create(it)
        }
    }

    override fun updateApplicationStatus(applicationId: String, status: Status): Application {
        val application = applicationRepository.findOneById(ObjectId(applicationId))
        val result = applicationRepository.save(application.copy(status = status))

        return Application.create(result)
    }

    override fun listAllApplications(): List<Application> {
        return applicationRepository.findAll().map {
            Application.create(it)
        }
    }

    override fun getApplicationByCandidateId(id: String): List<Application> {
        return applicationRepository.findAllByCandidateId(ObjectId(id)).toList().map {
            Application.create(it)
        };
    }

    override fun getApplicationById(id: String): Application {
        return Application.create(applicationRepository.findOneById(ObjectId(id)))
    }

    override fun saveApplication(request: ApplicationRequest): Application {
        val result = applicationRepository.save(ApplicationDTO(
                candidateId = ObjectId(request.candidateId),
                positionId = ObjectId(request.positionId)
        ))

        return Application.create(result)
    }
}
