package com.shneider.hrautomation.service.candidate

import com.shneider.hrautomation.data.candidate.Candidate
import com.shneider.hrautomation.data.candidate.CandidateDTO
import com.shneider.hrautomation.data.candidate.CandidateRepository
import com.shneider.hrautomation.data.user.UserRepository
import com.shneider.hrautomation.request.ApplicationRequest
import com.shneider.hrautomation.request.CandidateRequest
import com.shneider.hrautomation.service.application.ApplicationService
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class CandidateServiceImpl(
        private val candidateRepository: CandidateRepository,
        private val applicationService: ApplicationService,
        private val userRepository: UserRepository
) : CandidateService {
    override fun applyForPosition(request: ApplicationRequest) {
        applicationService.saveApplication(request);
    }

    override fun deleteCandidate(username: String) {
        candidateRepository.deleteByUsername(username)
    }

    override fun updateCandidate(username: String, request: CandidateRequest) {
        val candidate = userRepository.findByUsername(username)

        if (candidate.isPresent) {
            candidateRepository.save(CandidateDTO(
                    description = request.description,
                    name = request.name,
                    worksInCompany = request.worksInCompany,
                    username = username
            ))
        }
    }

    override fun getCandidateById(id: String): Candidate {
        return Candidate.create(candidateRepository.findOneById(ObjectId(id)))
    }

    override fun getCandidateByUsername(username: String): Candidate {
        return Candidate.create(candidateRepository.findOneByUsername(username))
    }
}
