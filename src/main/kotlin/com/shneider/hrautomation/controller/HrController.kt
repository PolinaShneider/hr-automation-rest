package com.shneider.hrautomation.controller

import com.shneider.hrautomation.data.application.Application
import com.shneider.hrautomation.data.hr.Hr
import com.shneider.hrautomation.data.interview.Interview
import com.shneider.hrautomation.data.interviewer.Interviewer
import com.shneider.hrautomation.data.position.Position
import com.shneider.hrautomation.data.team.Team
import com.shneider.hrautomation.request.PositionRequest
import com.shneider.hrautomation.request.StatusRequest
import com.shneider.hrautomation.service.application.ApplicationService
import com.shneider.hrautomation.service.hr.HrService
import com.shneider.hrautomation.service.interview.InterviewService
import com.shneider.hrautomation.service.interviewer.InterviewerService
import com.shneider.hrautomation.service.position.PositionService
import com.shneider.hrautomation.service.team.TeamService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/hr")
@PreAuthorize("hasRole('HR')")
class HrController(
        private val hrService: HrService,
        private val positionService: PositionService,
        private val applicationService: ApplicationService,
        private val interviewService: InterviewService,
        private val teamService: TeamService,
        private val interviewerService: InterviewerService
) {
    @PostMapping("/create-position")
    fun createPosition(@RequestBody request: PositionRequest): ResponseEntity<Position> {
        val position = hrService.createPosition(request)
        return ResponseEntity(position, HttpStatus.CREATED)
    }

    @PostMapping("/{id}/update-position")
    fun updatePosition(@PathVariable("id") id: String, @RequestBody request: PositionRequest): ResponseEntity<Void> {
        hrService.updatePosition(id, request)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping("/get-positions")
    fun getPositions(): ResponseEntity<List<Position>> {
        return ResponseEntity(positionService.listAllPositions(), HttpStatus.OK)
    }

    @GetMapping("/get-teams")
    fun getTeams(): ResponseEntity<List<Team>> {
        return ResponseEntity(teamService.listAllTeams(), HttpStatus.OK)
    }

    @GetMapping("/get-applications")
    fun getApplications(): ResponseEntity<List<Application>> {
        return ResponseEntity(applicationService.listAllApplications(), HttpStatus.OK)
    }

    @GetMapping("/get-interviewers")
    fun getInterviewers(): ResponseEntity<List<Interviewer>> {
        return ResponseEntity(interviewerService.getInterviewers(), HttpStatus.OK)
    }

    @GetMapping("/get-rotation-applications")
    fun getRotationApplications(): ResponseEntity<List<Application>> {
        return ResponseEntity(applicationService.listRotationApplications(), HttpStatus.OK)
    }

    @GetMapping("/get-interview-info/{id}")
    fun getInterviewInfo(@PathVariable("id") id: String): ResponseEntity<Interview> {
        return ResponseEntity(interviewService.getInterview(id), HttpStatus.OK)
    }

    @PostMapping("/{id}/update-application")
    fun updateApplication(@PathVariable("id") id: String, @RequestBody request: StatusRequest): ResponseEntity<Application> {
        return ResponseEntity(hrService.updateApplication(id, request), HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getHr(
            @PathVariable("id") id: String
    ): ResponseEntity<Hr> {
        val result = hrService.getHrByUsername(id);
        return ResponseEntity.ok(result);
    }
}