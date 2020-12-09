package com.shneider.hrautomation.request

import com.shneider.hrautomation.data.application.Status

data class StatusRequest(
        val interviewerId: String? = null,
        val status: Status
)