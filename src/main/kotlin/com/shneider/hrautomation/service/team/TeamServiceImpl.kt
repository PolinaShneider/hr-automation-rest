package com.shneider.hrautomation.service.team

import com.shneider.hrautomation.data.team.Team
import com.shneider.hrautomation.data.team.TeamRepository
import org.springframework.stereotype.Service


@Service
class TeamServiceImpl(
        private val teamRepository: TeamRepository
) : TeamService {
    override fun listAllTeams(): List<Team> {
        return teamRepository.findAll().map { Team.create(it) }
    }
}
