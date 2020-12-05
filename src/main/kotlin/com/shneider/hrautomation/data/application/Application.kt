package com.shneider.hrautomation.data.application

class Application(
        private var id: String,
        private var candidateId: String,
        private var positionId: String,
        private var status: Status = Status.PENDING,
        private var isRotation: Boolean = false
) {
    fun getCandidateId(): String {
        return candidateId
    }

    fun getPositionId(): String {
        return positionId
    }

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
    PENDING,
    INTERVIEW_UPCOMING,
    INTERVIEW_PASSED,
    OFFER,
    REJECT
}
