package com.shneider.hrautomation.controller

import com.shneider.hrautomation.data.application.Application
import com.shneider.hrautomation.data.application.Status
import com.shneider.hrautomation.data.candidate.Candidate
import com.shneider.hrautomation.data.position.Position
import com.shneider.hrautomation.request.ApplicationRequest
import com.shneider.hrautomation.request.PositionRequest
import com.shneider.hrautomation.service.application.ApplicationService
import com.shneider.hrautomation.service.hr.HrService
import com.shneider.hrautomation.service.position.PositionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/hr")
@PreAuthorize("hasRole('HR')")
class HrController(
        private val hrService: HrService,
        private val positionService: PositionService,
        private val applicationService: ApplicationService
) {
    @PostMapping("/create-position")
    fun createPosition(@RequestBody request: PositionRequest): ResponseEntity<Position> {
        val position = hrService.createPosition(request)
        return ResponseEntity(position, HttpStatus.CREATED)
    }

    @PostMapping("/{id}/update-position")
    fun updatePosition(@PathVariable("id") id: String, @RequestBody request: PositionRequest): ResponseEntity<Candidate> {
        hrService.updatePosition(id, request)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping("/get-positions")
    fun getPositions(): ResponseEntity<List<Position>> {
        val positions = positionService.listAllPositions()
        return ResponseEntity(positions, HttpStatus.OK)
    }

    @GetMapping("/get-applications")
    fun getApplications(): ResponseEntity<List<Application>> {
        val applications = applicationService.listAllApplications()
        return ResponseEntity(applications, HttpStatus.OK)
    }

    @GetMapping("/get-rotation-applications")
    fun getRotationApplications(): ResponseEntity<List<Application>> {
        val applications = applicationService.listRotationApplications()
        return ResponseEntity(applications, HttpStatus.OK)
    }

    @PostMapping("/{id}/update-application")
    fun updateApplications(@PathVariable("id") id: String, status: Status): ResponseEntity<Application> {
        return ResponseEntity(hrService.updateApplication(id, status), HttpStatus.CREATED)
    }
}