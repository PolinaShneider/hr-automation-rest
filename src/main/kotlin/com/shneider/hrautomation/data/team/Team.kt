package com.shneider.hrautomation.data.team

class Team(
        private var id: String,
        private var title: String
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