package com.shneider.hrautomation.service.candidate

import com.shneider.hrautomation.data.application.Application
import com.shneider.hrautomation.data.candidate.Candidate
import com.shneider.hrautomation.data.interview.Interview
import com.shneider.hrautomation.request.ApplicationRequest
import com.shneider.hrautomation.request.CandidateRequest


interface CandidateService {

    fun getCandidateById(id: String): Candidate

    fun getCandidateByUsername(username: String): Candidate

    fun updateInfo(username: String, request: CandidateRequest)

    fun deleteCandidate(username: String)

    fun applyForPosition(request: ApplicationRequest)

    fun getMyApplications(id: String): List<Application>

    fun getMyInterviews(id: String): List<Interview>
}
