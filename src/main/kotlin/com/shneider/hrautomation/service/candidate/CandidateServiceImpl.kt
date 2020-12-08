package com.shneider.hrautomation.service.candidate

import com.shneider.hrautomation.data.application.Application
import com.shneider.hrautomation.data.candidate.Candidate
import com.shneider.hrautomation.data.candidate.CandidateDTO
import com.shneider.hrautomation.data.candidate.CandidateRepository
import com.shneider.hrautomation.data.interview.Interview
import com.shneider.hrautomation.data.user.UserRepository
import com.shneider.hrautomation.request.ApplicationRequest
import com.shneider.hrautomation.request.CandidateRequest
import com.shneider.hrautomation.service.application.ApplicationService
import com.shneider.hrautomation.service.interview.InterviewService
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import java.lang.NumberFormatException

@Service
class CandidateServiceImpl(
        private val candidateRepository: CandidateRepository,
        private val applicationService: ApplicationService,
        private val userRepository: UserRepository,
        private val interviewService: InterviewService
) : CandidateService {
    override fun getMyInterviews(id: String): List<Interview> {
        return interviewService.getAllByCandidateId(id)
    }

    override fun getMyApplications(id: String): List<Application> {
        return applicationService.getApplicationByCandidateId(id);
    }

    override fun applyForPosition(request: ApplicationRequest) {
        applicationService.saveApplication(request);
    }

    override fun deleteCandidate(username: String) {
        candidateRepository.deleteByUsername(username)
    }

    override fun updateInfo(username: String, request: CandidateRequest) {
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
        val candidate: CandidateDTO?;
        try {
            candidate = candidateRepository.findOneById(ObjectId(id))
        } catch (e: NumberFormatException) {
            throw InternalError("Cannot parse provided id $id")
        }

        if (candidate != null) {
            return Candidate.create(candidate)
        }

        throw NoSuchElementException("Candidate with id $id not found")
    }

    override fun getCandidateByUsername(username: String): Candidate {
        val candidate = candidateRepository.findOneByUsername(username)

        if (candidate != null) {
            return Candidate.create(candidate)
        }

        throw NoSuchElementException("Candidate with username $username not found")
    }
}
