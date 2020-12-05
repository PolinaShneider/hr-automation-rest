package com.shneider.hrautomation.request

import com.shneider.hrautomation.data.interview.Feedback

data class FeedbackRequest(
        val feedback: Feedback = Feedback.NONE
)