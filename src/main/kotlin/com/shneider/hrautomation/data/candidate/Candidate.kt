package com.shneider.hrautomation.data.candidate

import com.shneider.hrautomation.data.BaseUser
import com.shneider.hrautomation.data.application.Status
import org.springframework.data.mongodb.core.mapping.Document

class Candidate(
        override var name: String,
        override var bio: String,
        override var id: String,
        override val username: String,
        var worksInCompany: Boolean
) : BaseUser {
    companion object {
        fun create(result: CandidateDTO): Candidate {
            return Candidate(
                    name = result.name,
                    bio = result.description,
                    id = result.id.toString(),
                    username = result.username,
                    worksInCompany = result.worksInCompany
            )
        }
    }

    fun notifyMe(status: Status) {
        //
    }
}