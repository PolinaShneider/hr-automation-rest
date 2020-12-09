package com.shneider.hrautomation.data.position

class Position(
        var teamId: String,
        var title: String,
        var requirements: String,
        var isOpened: Boolean = true
) {
    companion object {
        fun create(result: PositionDTO): Position {
            return Position(
                    teamId = result.teamId,
                    title = result.title,
                    requirements = result.requirements,
                    isOpened = result.open
            )
        }
    }
}