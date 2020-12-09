package com.shneider.hrautomation.data.team

class Team(
        val alias: String,
        val title: String
) {
    companion object {
        fun create(result: TeamDTO): Team {
            return Team(
                    alias = result.alias,
                    title = result.title
            )
        }
    }
}