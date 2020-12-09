package com.shneider.hrautomation.data.application

class Application(
        private var id: String,
        private var candidateId: String,
        private var positionId: String,
        var status: Status = Status.PENDING,
        var isRotation: Boolean = false,
        var interviewId: String? = null
) {
    fun getCandidateId(): String {
        return candidateId
    }

    fun getPositionId(): String {
        return positionId
    }

    fun getApplicationId(): String {
        return id
    }

    companion object {
        fun create(result: ApplicationDTO): Application {
            return Application(
                    id = result.id.toString(),
                    candidateId = result.candidateId.toString(),
                    positionId = result.positionId.toString(),
                    status = result.status,
                    isRotation = result.rotation,
                    interviewId = result.interviewId
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
