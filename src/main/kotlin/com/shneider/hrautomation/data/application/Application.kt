package com.shneider.hrautomation.data.application

class Application(
        private var id: String,
        private var candidateId: String,
        private var positionId: String,
        private var status: Status = Status.DRAFT,
        private var isRotation: Boolean = false
) {
    companion object {
        fun create(result: ApplicationDTO): Application {
            return Application(
                    id = result.id.toString(),
                    candidateId = result.candidateId.toString(),
                    positionId = result.positionId.toString(),
                    status = result.status,
                    isRotation = result.isRotation
            )
        }
    }
}

enum class Status {
    DRAFT,
    PENDING,
    INTERVIEW_UPCOMING,
    INTERVIEW_PASSED,
    OFFER,
    REJECT
}
