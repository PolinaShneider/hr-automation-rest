package com.shneider.hrautomation.data.interviewer

import com.shneider.hrautomation.data.BaseUser
import com.shneider.hrautomation.data.application.Status
import org.springframework.data.mongodb.core.mapping.Document

class Interviewer(
        override var name: String,
        override var bio: String,
        override var id: String,
        override val username: String,
        var interviews: List<Int> = emptyList()
) : BaseUser {
    fun notifyMe(status: Status) {
        //
    }

    companion object {
        fun create(result: InterviewerDTO): Interviewer {
            return Interviewer(
                    name = result.name,
                    bio = result.description,
                    id = result.id.toString(),
                    username = result.username,
                    interviews = result.interviews
            )
        }
    }
}