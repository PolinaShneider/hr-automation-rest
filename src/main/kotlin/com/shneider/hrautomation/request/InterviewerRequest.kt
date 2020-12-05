package com.shneider.hrautomation.request

data class InterviewerRequest(
        val name: String,
        val description: String,
        val interviews: List<String> = emptyList()
)