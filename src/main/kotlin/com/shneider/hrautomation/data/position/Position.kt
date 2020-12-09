package com.shneider.hrautomation.data.position

class Position(
        val teamId: String,
        val title: String,
        val requirements: String,
        val isOpened: Boolean = true,
        val id: String
) {
    companion object {
        fun create(result: PositionDTO): Position {
            return Position(
                    teamId = result.teamId,
                    title = result.title,
                    requirements = result.requirements,
                    isOpened = result.open,
                    id = result.id.toString()
            )
        }
    }
}