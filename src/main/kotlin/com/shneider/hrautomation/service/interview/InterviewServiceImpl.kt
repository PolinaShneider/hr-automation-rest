package com.shneider.hrautomation.service.interview

import com.shneider.hrautomation.data.interview.Interview
import com.shneider.hrautomation.data.interview.InterviewDTO
import com.shneider.hrautomation.data.interview.InterviewRepository
import com.shneider.hrautomation.request.InterviewRequest
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class InterviewServiceImpl(
        private val interviewsRepository: InterviewRepository
) : InterviewService {
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
        return interviewsRepository.findAllByInterviewerId(ObjectId(id)).map {
            Interview.create(it)
        }
    }

    override fun getInterview(id: String): Interview {
        return Interview.create(interviewsRepository.findOneById(ObjectId(id)))
    }

}
