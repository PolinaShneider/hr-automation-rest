package com.shneider.hrautomation.request

class PositionRequest(
        val title: String,
        val requirements: String,
        val isOpened: Boolean,
        val teamId: String
)