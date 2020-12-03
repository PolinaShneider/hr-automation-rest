package com.shneider.hrautomation.service.interviewer

import com.shneider.hrautomation.data.interview.Feedback
import com.shneider.hrautomation.data.interview.Interview
import com.shneider.hrautomation.data.interviewer.Interviewer
import com.shneider.hrautomation.data.interviewer.InterviewerRepository
import com.shneider.hrautomation.service.interview.InterviewService
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class InterviewerServiceImpl(
        private val interviewersRepository: InterviewerRepository,
        private val interviewService: InterviewService
) : InterviewerService {
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
