package com.shneider.hrautomation.controller

import com.shneider.hrautomation.data.candidate.Candidate
import com.shneider.hrautomation.request.ApplicationRequest
import com.shneider.hrautomation.request.CandidateRequest
import com.shneider.hrautomation.service.candidate.CandidateService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/candidate")
class CandidateController(
        private val candidateService: CandidateService
) {
    @PostMapping("/apply")
    fun applyForPosition(@RequestBody request: ApplicationRequest): ResponseEntity<Candidate> {
        candidateService.applyForPosition(request);
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateCandidate(
            @RequestBody request: CandidateRequest, @PathVariable("id") id: String
    ): ResponseEntity<Candidate> {
        candidateService.updateCandidate(id, request)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getCandidate(
            @PathVariable("id") id: String
    ): ResponseEntity<Candidate> {
        val result = candidateService.getCandidateById(id);
        return ResponseEntity.ok(result);
    }
}