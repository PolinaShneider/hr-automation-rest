package com.shneider.hrautomation.data.hr

import com.shneider.hrautomation.data.BaseUser
import com.shneider.hrautomation.data.application.Status

class Hr(
        override var name: String,
        override var bio: String,
        override var id: String,
        override val username: String
) : BaseUser {
    fun notifyMe(status: Status) {
        //
    }

    companion object {
        fun create(result: HrDTO): Hr {
            return Hr(
                    name = result.name,
                    bio = result.description,
                    id = result.id.toString(),
                    username = result.username
            )
        }
    }
}