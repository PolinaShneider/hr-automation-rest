package com.shneider.hrautomation.data.position

import com.shneider.hrautomation.data.User
import com.shneider.hrautomation.data.application.Status
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Position(
        var teamId: String,
        var title: String,
        var requirements: String,
        var isOpened: Boolean = true
) {
    companion object {
        fun create(result: PositionDTO): Position {
            return Position(
                    teamId = result.id.toString(),
                    title = result.title,
                    requirements = result.requirements,
                    isOpened = result.isOpened
            )
        }
    }
}