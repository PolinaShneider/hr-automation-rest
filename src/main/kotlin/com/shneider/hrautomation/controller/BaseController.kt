package com.shneider.hrautomation.controller

import com.shneider.hrautomation.data.candidate.Candidate
import com.shneider.hrautomation.request.ApplicationRequest
import com.shneider.hrautomation.request.CandidateRequest
import com.shneider.hrautomation.service.candidate.CandidateService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/base")
class BaseController(
        private val candidateService: CandidateService
) {
    @PostMapping("/create-candidate")
    fun createCandidate(@RequestBody request: CandidateRequest): ResponseEntity<Candidate> {
        candidateService.createCandidate(request)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @DeleteMapping("/delete-candidate/{id}")
    fun deleteCandidate(
            @PathVariable("id") id: String
    ): ResponseEntity<Candidate> {
        candidateService.deleteCandidate(id);
        return ResponseEntity(HttpStatus.ACCEPTED)
    }
}