package com.shneider.hrautomation.controller

import com.shneider.hrautomation.data.application.Application
import com.shneider.hrautomation.data.candidate.Candidate
import com.shneider.hrautomation.data.interview.Interview
import com.shneider.hrautomation.data.position.Position
import com.shneider.hrautomation.data.team.Team
import com.shneider.hrautomation.request.ApplicationRequest
import com.shneider.hrautomation.service.candidate.CandidateService
import com.shneider.hrautomation.service.position.PositionService
import com.shneider.hrautomation.service.team.TeamService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/candidate")
@PreAuthorize("hasRole('CANDIDATE')")
class CandidateController(
        private val candidateService: CandidateService,
        private val positionService: PositionService,
        private val teamService: TeamService
) {
    @PostMapping("/apply")
    fun applyForPosition(@RequestBody request: ApplicationRequest): ResponseEntity<String> {
        val applicationId = candidateService.applyForPosition(request);
        return ResponseEntity(applicationId, HttpStatus.CREATED)
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

    @GetMapping("/applications/{applicationId}")
    fun getApplication(
            @PathVariable("applicationId") applicationId: String
    ): ResponseEntity<Application> {
        val result = candidateService.getApplication(applicationId)
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/interviews")
    fun getInterviews(
            @PathVariable("id") id: String
    ): ResponseEntity<List<Interview>> {
        val result = candidateService.getMyInterviews(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/open-positions")
    fun getOpenPositions(): ResponseEntity<List<Position>> {
        val result = positionService.listOpenPositions()
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get-teams")
    fun getTeams(): ResponseEntity<List<Team>> {
        return ResponseEntity(teamService.listAllTeams(), HttpStatus.OK)
    }
}