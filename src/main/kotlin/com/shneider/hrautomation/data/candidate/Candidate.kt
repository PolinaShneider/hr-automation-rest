package com.shneider.hrautomation.data.candidate

import com.shneider.hrautomation.data.User
import com.shneider.hrautomation.data.application.Status
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Candidate(
        override var name: String,
        override var bio: String,
        override var id: String,
        var worksInCompany: Boolean
) : User {
    companion object {
        fun create(result: CandidateDTO): Candidate {
            return Candidate(
                    name = result.name,
                    bio = result.description,
                    id = result.id.toString(),
                    worksInCompany = result.worksInCompany
            )
        }
    }
    fun notifyMe(status: Status) {
        //
    }
}