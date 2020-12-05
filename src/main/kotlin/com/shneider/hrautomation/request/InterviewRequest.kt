package com.shneider.hrautomation.request

data class InterviewRequest(
        val candidateId: String,
        val positionId: String,
        val interviewerId: String
)