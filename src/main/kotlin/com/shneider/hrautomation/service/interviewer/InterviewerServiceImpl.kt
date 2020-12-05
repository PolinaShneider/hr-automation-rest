package com.shneider.hrautomation.service.interviewer

import com.shneider.hrautomation.data.interview.Feedback
import com.shneider.hrautomation.data.interview.Interview
import com.shneider.hrautomation.data.interviewer.Interviewer
import com.shneider.hrautomation.data.interviewer.InterviewerDTO
import com.shneider.hrautomation.data.interviewer.InterviewerRepository
import com.shneider.hrautomation.data.user.UserRepository
import com.shneider.hrautomation.request.InterviewerRequest
import com.shneider.hrautomation.service.interview.InterviewService
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class InterviewerServiceImpl(
        private val interviewersRepository: InterviewerRepository,
        private val interviewService: InterviewService,
        private val userRepository: UserRepository
) : InterviewerService {
    override fun updateInfo(username: String, interviewerRequest: InterviewerRequest) {
        val interviewer = userRepository.findByUsername(username)

        if (interviewer.isPresent) {
            interviewersRepository.save(InterviewerDTO(
                    name = interviewerRequest.name,
                    description = interviewerRequest.description,
                    interviews = interviewerRequest.interviews,
                    username = username
            ))
        }
    }

    override fun conductInterview(id: String, feedback: Feedback): Interview {
        val interview = interviewService.getInterview(id)

        interview.conduct(feedback)

        return interview;
    }

    override fun getAvailableInterviewer(): Interviewer {
        return Interviewer.create(interviewersRepository.findAll().first())
    }

    override fun getInterviewer(id: String): Interviewer {
        return Interviewer.create(interviewersRepository.findOneById(ObjectId(id)))
    }
}
