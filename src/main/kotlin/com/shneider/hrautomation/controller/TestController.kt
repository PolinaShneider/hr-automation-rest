package com.shneider.hrautomation.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/test")
class TestController {
    @GetMapping("/all")
    fun allAccess(): String {
        return "Public Content."
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('HR') or hasRole('INTERVIEWER') or hasRole('ADMIN')")
    fun userAccess(): String {
        return "User Content."
    }

    @GetMapping("/hr")
    @PreAuthorize("hasRole('HR') or hasRole('ADMIN')")
    fun hrAccess(): String {
        return "HR Board."
    }

    @GetMapping("/interviewer")
    @PreAuthorize("hasRole('INTERVIEWER') or hasRole('ADMIN')")
    fun interviewerAccess(): String {
        return "Admin Board."
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('CANDIDATE') or hasRole('ADMIN')")
    fun candidateAccess(): String {
        return "Candidate Board."
    }
}