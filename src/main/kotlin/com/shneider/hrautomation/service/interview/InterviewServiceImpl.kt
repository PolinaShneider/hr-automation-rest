package com.shneider.hrautomation.service.interview

import com.shneider.hrautomation.data.application.Status
import com.shneider.hrautomation.data.interview.Feedback
import com.shneider.hrautomation.data.interview.Interview
import com.shneider.hrautomation.data.interview.InterviewDTO
import com.shneider.hrautomation.data.interview.InterviewRepository
import com.shneider.hrautomation.request.InterviewRequest
import com.shneider.hrautomation.service.application.ApplicationService
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class InterviewServiceImpl(
        private val interviewsRepository: InterviewRepository,
        private val applicationService: ApplicationService
) : InterviewService {
    override fun saveInterviewResult(interview: Interview): Interview {
        val original = interviewsRepository.findOneById(ObjectId(interview.id))
        val dto = interviewsRepository.save(original.copy(feedback = interview.feedback, status = interview.status))
        applicationService.postInterviewUpdate(ObjectId(interview.candidateId), ObjectId(interview.positionId), interview.status)
        return Interview.create(dto)
    }

    override fun getAllByCandidateId(id: String): List<Interview> {
        return interviewsRepository.findAllByCandidateIdOrderByModifiedDateDesc(id).map {
            Interview.create(it)
        }
    }

    override fun getInterviewFeedback(id: String): Feedback {
        val interview = getInterview(id)

        return if (interview.status == Status.INTERVIEW_PASSED) {
            interview.feedback
        } else {
            throw IllegalStateException("Interview has not passed yet")
        }
    }

    override fun schedule(request: InterviewRequest): Interview {
        val result = interviewsRepository.save(
                InterviewDTO(
                        candidateId = request.candidateId,
                        positionId = request.positionId,
                        interviewerId = ObjectId(request.interviewerId)
                )
        )

        return Interview.create(result)
    }

    override fun update(id: String, request: InterviewRequest): Interview {
        val interview = interviewsRepository.findOneById(ObjectId(id))
        val result = interviewsRepository.save(
                interview.copy(
                        candidateId = request.candidateId,
                        positionId = request.positionId
                )
        )

        return Interview.create(result)
    }

    override fun getAllByInterviewerId(id: String): List<Interview> {
        return interviewsRepository.findAllByInterviewerIdOrderByModifiedDateDesc(ObjectId(id)).map {
            Interview.create(it)
        }
    }

    override fun getInterview(id: String): Interview {
        return Interview.create(interviewsRepository.findOneById(ObjectId(id)))
    }

}
