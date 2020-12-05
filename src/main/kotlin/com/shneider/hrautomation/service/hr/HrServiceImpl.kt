package com.shneider.hrautomation.service.hr

import com.shneider.hrautomation.data.interviewer.Interviewer
import com.shneider.hrautomation.data.application.Application
import com.shneider.hrautomation.data.application.Status
import com.shneider.hrautomation.data.hr.HrDTO
import com.shneider.hrautomation.data.hr.HrRepository
import com.shneider.hrautomation.data.interview.Feedback
import com.shneider.hrautomation.data.interview.Interview
import com.shneider.hrautomation.data.interviewer.InterviewerRepository
import com.shneider.hrautomation.data.user.User
import com.shneider.hrautomation.data.user.UserRepository
import com.shneider.hrautomation.request.HrRequest
import com.shneider.hrautomation.request.InterviewRequest
import com.shneider.hrautomation.request.PositionRequest
import com.shneider.hrautomation.service.application.ApplicationService
import com.shneider.hrautomation.service.interview.InterviewService
import com.shneider.hrautomation.service.interviewer.InterviewerService
import com.shneider.hrautomation.service.position.PositionService
import org.springframework.stereotype.Service


@Service
class HrServiceImpl(
        private val applicationService: ApplicationService,
        private val positionService: PositionService,
        private val interviewService: InterviewService,
        private val interviewerService: InterviewerService,
        private val hrRepository: HrRepository,
        private val userRepository: UserRepository
) : HrService {
    override fun updateInfo(username: String, hrRequest: HrRequest) {
        val hr = userRepository.findByUsername(username)

        if (hr.isPresent) {
            hrRepository.save(HrDTO(
                    name = hrRequest.name,
                    description = hrRequest.description,
                    username = username
            ))
        }
    }

    override fun updatePosition(positionId: String, request: PositionRequest) {
        positionService.updatePosition(positionId, request)
    }

    override fun createPosition(request: PositionRequest) {
        positionService.savePosition(request)
    }

    override fun notifyMe(status: Status, applicationId: String, feedback: Feedback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun scheduleInterview(request: InterviewRequest): Interview {
        return interviewService.schedule(request)
    }

    override fun updateApplication(applicationId: String, status: Status): Application {
        return applicationService.updateApplicationStatus(applicationId, status)
    }

    override fun getAvailableInterviewer(): Interviewer {
        return interviewerService.getAvailableInterviewer()
    }

}
