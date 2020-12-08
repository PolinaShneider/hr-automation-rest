package com.shneider.hrautomation.service.candidate

import com.shneider.hrautomation.data.candidate.Candidate
import com.shneider.hrautomation.request.ApplicationRequest
import com.shneider.hrautomation.request.CandidateRequest


interface CandidateService {

    fun getCandidateById(username: String): Candidate

    fun getCandidateByUsername(username: String): Candidate

    fun updateInfo(username: String, request: CandidateRequest)

    fun deleteCandidate(username: String)

    fun applyForPosition(request: ApplicationRequest)
}
