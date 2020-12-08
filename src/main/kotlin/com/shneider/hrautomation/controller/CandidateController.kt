package com.shneider.hrautomation.controller

import com.shneider.hrautomation.data.application.Application
import com.shneider.hrautomation.data.candidate.Candidate
import com.shneider.hrautomation.data.interview.Interview
import com.shneider.hrautomation.request.ApplicationRequest
import com.shneider.hrautomation.request.CandidateRequest
import com.shneider.hrautomation.service.candidate.CandidateService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/candidate")
@PreAuthorize("hasRole('CANDIDATE')")
class CandidateController(
        private val candidateService: CandidateService
) {
    @PostMapping("/apply")
    fun applyForPosition(@RequestBody request: ApplicationRequest): ResponseEntity<Candidate> {
        candidateService.applyForPosition(request);
        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getCandidate(
            @PathVariable("id") id: String
    ): ResponseEntity<Candidate> {
        val result = candidateService.getCandidateByUsername(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/applications")
    fun getApplications(
            @PathVariable("id") id: String
    ): ResponseEntity<List<Application>> {
        val result = candidateService.getMyApplications(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/interviews")
    fun getInterviews(
            @PathVariable("id") id: String
    ): ResponseEntity<List<Interview>> {
        val result = candidateService.getMyInterviews(id);
        return ResponseEntity.ok(result);
    }
}