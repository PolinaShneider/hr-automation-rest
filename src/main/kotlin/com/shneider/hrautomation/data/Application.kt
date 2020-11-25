package com.shneider.hrautomation.data

class Application {
    var id: Int? = 0
    var candidateId: Int? = 0
    var positionId: Int? = 0
    var status: Status = Status.DRAFT
}

enum class Status {
    DRAFT,
    PENDING,
    INTERVIEW_UPCOMING,
    INTERVIEW_PASSED,
    OFFER,
    REJECT
}
