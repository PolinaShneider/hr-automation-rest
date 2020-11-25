package com.shneider.hrautomation.data

import org.springframework.data.mongodb.core.mapping.Document

@Document
class Candidate(
        override var name: String,
        override var bio: String,
        override var id: Int,
        var worksInCompany: Boolean
) : User {
    fun notifyMe(status: Status) {
        //
    }
}