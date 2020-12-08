package com.shneider.hrautomation.controller

import com.shneider.hrautomation.request.CandidateRequest
import com.shneider.hrautomation.request.HrRequest
import com.shneider.hrautomation.request.InterviewerRequest
import com.shneider.hrautomation.service.candidate.CandidateService
import com.shneider.hrautomation.service.hr.HrService
import com.shneider.hrautomation.service.interviewer.InterviewerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/user")
class UserController(
        private val candidateService: CandidateService,
        private val interviewerService: InterviewerService,
        private val hrService: HrService
) {
    @PostMapping("/{username}/updateHrInfo")
    @PreAuthorize("hasRole('HR')")
    fun updateHrInfo(@PathVariable("username") username: String, @Valid @RequestBody hrRequest: HrRequest): ResponseEntity<Void> {
        hrService.updateInfo(username, hrRequest)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PostMapping("/{username}/updateCandidateInfo")
    @PreAuthorize("hasRole('CANDIDATE')")
    fun updateCandidateInfo(@PathVariable("username") username: String, @Valid @RequestBody candidateRequest: CandidateRequest): ResponseEntity<Void> {
        candidateService.updateInfo(username, candidateRequest)
        return ResponseEntity(HttpStatus.CREATED)
    }


    @PostMapping("/{username}/updateInterviewerInfo")
    @PreAuthorize("hasRole('INTERVIEWER')")
    fun updateInterviewerInfo(@PathVariable("username") username: String, @Valid @RequestBody interviewerRequest: InterviewerRequest): ResponseEntity<Void> {
        interviewerService.updateInfo(username, interviewerRequest)
        return ResponseEntity(HttpStatus.CREATED)
    }
}
