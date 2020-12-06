package com.shneider.hrautomation.service.team

import com.shneider.hrautomation.data.position.Position
import com.shneider.hrautomation.data.team.Team


interface TeamService {
    fun listAllTeams(): List<Team>
}
