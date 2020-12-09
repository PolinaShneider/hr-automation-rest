package com.shneider.hrautomation.data.team

class Team(
        var id: String,
        var title: String
) {
    companion object {
        fun create(result: TeamDTO): Team {
            return Team(
                    id = result.id.toString(),
                    title = result.title
            )
        }
    }
}