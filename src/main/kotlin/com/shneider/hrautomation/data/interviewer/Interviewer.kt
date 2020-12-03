package com.shneider.hrautomation.data.interviewer

import com.shneider.hrautomation.data.User
import com.shneider.hrautomation.data.application.Status
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Interviewer(
        override var name: String,
        override var bio: String,
        override var id: String,
        var interviews: List<Int> = emptyList()
) : User {
    fun notifyMe(status: Status) {
        //
    }

    companion object {
        fun create(result: InterviewerDTO): Interviewer {
            return Interviewer(
                    name = result.name,
                    bio = result.description,
                    id = result.id.toString(),
                    interviews = result.interviews
            )
        }
    }
}