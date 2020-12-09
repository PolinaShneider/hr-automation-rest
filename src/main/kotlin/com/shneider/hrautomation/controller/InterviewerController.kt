package com.shneider.hrautomation.controller

import com.shneider.hrautomation.data.interview.Interview
import com.shneider.hrautomation.data.interviewer.Interviewer
import com.shneider.hrautomation.request.FeedbackRequest
import com.shneider.hrautomation.service.interview.InterviewService
import com.shneider.hrautomation.service.interviewer.InterviewerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/interviewer")
@PreAuthorize("hasRole('INTERVIEWER')")
class InterviewerController(
        private val interviewerService: InterviewerService,
        private val interviewService: InterviewService
) {
    @PostMapping("/conduct-interview/{id}")
    fun conductInterview(
        @PathVariable("id") id: String, @RequestBody request: FeedbackRequest
    ): ResponseEntity<Interview> {
        val result = interviewerService.conductInterview(id, request.feedback)
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{username}/upcoming-interviews")
    fun getInterviews(
            @PathVariable("username") username: String
    ): ResponseEntity<List<Interview>> {
        val id = interviewerService.getInterviewerByUsername(username).id;
        val interviews = interviewService.getAllByInterviewerId(id)
        return ResponseEntity(interviews, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getInterviewer(
            @PathVariable("id") id: String
    ): ResponseEntity<Interviewer> {
        val result = interviewerService.getInterviewerByUsername(id);
        return ResponseEntity.ok(result);
    }
}