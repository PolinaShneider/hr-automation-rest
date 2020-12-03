package com.shneider.hrautomation.service.candidate

import com.shneider.hrautomation.data.candidate.Candidate
import com.shneider.hrautomation.request.ApplicationRequest
import com.shneider.hrautomation.request.CandidateRequest


interface CandidateService {

    fun getCandidateById(id: String): Candidate

    fun updateCandidate(id: String, request: CandidateRequest)

    fun createCandidate(request: CandidateRequest)

    fun deleteCandidate(id: String)

    fun applyForPosition(request: ApplicationRequest)
}
