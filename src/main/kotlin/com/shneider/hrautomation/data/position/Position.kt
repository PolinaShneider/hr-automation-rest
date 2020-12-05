package com.shneider.hrautomation.data.position

import org.springframework.data.mongodb.core.mapping.Document

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