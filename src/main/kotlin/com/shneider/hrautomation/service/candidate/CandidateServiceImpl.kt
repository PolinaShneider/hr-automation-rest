package com.shneider.hrautomation.service.candidate

import com.shneider.hrautomation.data.candidate.Candidate
import com.shneider.hrautomation.data.candidate.CandidateDTO
import com.shneider.hrautomation.data.candidate.CandidateRepository
import com.shneider.hrautomation.request.ApplicationRequest
import com.shneider.hrautomation.request.CandidateRequest
import com.shneider.hrautomation.service.application.ApplicationService
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CandidateServiceImpl(
        private val candidateRepository: CandidateRepository,
        private val applicationService: ApplicationService
) : CandidateService {
    override fun applyForPosition(request: ApplicationRequest) {
        applicationService.saveApplication(request);
    }

    override fun deleteCandidate(id: String) {
        candidateRepository.deleteById(id)
    }

    override fun updateCandidate(id: String, request: CandidateRequest) {
        val candidate = candidateRepository.findOneById(ObjectId(id))

        candidateRepository.save(CandidateDTO(
                id = candidate.id,
                name = request.name,
                description = request.bio,
                worksInCompany = request.worksInCompany,
                createdDate = candidate.createdDate,
                modifiedDate = LocalDateTime.now()
        ))
    }

    override fun getCandidateById(id: String): Candidate {
        return Candidate.create(candidateRepository.findOneById(ObjectId(id)))
    }

    override fun createCandidate(request: CandidateRequest) {
        candidateRepository.save(CandidateDTO(
                name = request.name,
                description = request.bio,
                worksInCompany = request.worksInCompany
        ))
    }
}
