package com.shneider.hrautomation.request

data class CandidateRequest(
        val name: String,
        val description: String,
        val worksInCompany: Boolean
)