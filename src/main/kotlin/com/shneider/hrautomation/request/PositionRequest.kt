package com.shneider.hrautomation.request

data class PositionRequest(
        val title: String,
        val requirements: String,
        val isOpened: Boolean,
        val teamId: String
)