package com.shneider.hrautomation

import com.shneider.hrautomation.data.candidate.Candidate
import com.shneider.hrautomation.data.candidate.CandidateDTO
import com.shneider.hrautomation.request.ApplicationRequest
import com.shneider.hrautomation.request.CandidateRequest
import com.shneider.hrautomation.request.PositionRequest
import com.shneider.hrautomation.service.candidate.CandidateService
import com.shneider.hrautomation.service.hr.HrService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/hr")
class HrController(
        private val hrService: HrService
) {
    @PostMapping("/update-position")
    fun createCandidate(@PathVariable("id") id: String, @RequestBody request: PositionRequest): ResponseEntity<Candidate> {
        hrService.updatePosition(id, request)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PostMapping("/create-position")
    fun applyForPosition(@RequestBody request: PositionRequest): ResponseEntity<Candidate> {
        hrService.createPosition(request)
        return ResponseEntity(HttpStatus.CREATED)
    }
}