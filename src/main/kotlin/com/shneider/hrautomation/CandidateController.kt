package com.shneider.hrautomation

import com.shneider.hrautomation.data.Candidate
import com.shneider.hrautomation.data.CandidateDTO
import com.shneider.hrautomation.data.CandidateRepository
import com.shneider.hrautomation.request.CandidateRequest
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/candidate")
class CandidateController(
        private val candidateRepository: CandidateRepository
) {

    @PostMapping
    fun createCandidate(@RequestBody request: CandidateRequest): ResponseEntity<Candidate> {
        candidateRepository.save(CandidateDTO(
                name = request.name,
                description = request.bio,
                worksInCompany = request.worksInCompany
        ))
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateCandidate(
            @RequestBody request: CandidateRequest, @PathVariable("id") id: String
    ): ResponseEntity<Candidate> {
        val candidate = candidateRepository.findOneById(ObjectId(id))

        candidateRepository.save(CandidateDTO(
                id = candidate.id,
                name = request.name,
                description = request.bio,
                worksInCompany = request.worksInCompany,
                createdDate = candidate.createdDate,
                modifiedDate = LocalDateTime.now()
        ))

        return ResponseEntity(HttpStatus.CREATED)
    }
}