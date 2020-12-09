package com.shneider.hrautomation.service.position

import com.shneider.hrautomation.data.position.Position
import com.shneider.hrautomation.data.position.PositionDTO
import com.shneider.hrautomation.data.position.PositionRepository
import com.shneider.hrautomation.request.PositionRequest
import org.bson.types.ObjectId
import org.springframework.stereotype.Service


@Service
class PositionServiceImpl(
        private val positionRepository: PositionRepository
) : PositionService {
    override fun updatePosition(positionId: String, request: PositionRequest) {
        val position = positionRepository.findOneById(ObjectId(positionId))

        positionRepository.save(
                position.copy(
                        title = request.title,
                        requirements = request.requirements,
                        teamId = request.teamId,
                        open = request.isOpened
                )
        )
    }

    override fun listAllPositions(): List<Position> {
        return positionRepository.findAll().map {
            Position.create(it)
        }
    }

    override fun listOpenPositions(): List<Position> {
        return positionRepository.findAllByOpenTrue().map {
            Position.create(it)
        }
    }

    override fun getPositionById(id: String): Position {
        return Position.create(positionRepository.findOneById(ObjectId(id)))
    }

    override fun savePosition(request: PositionRequest): Position {
        val result = positionRepository.save(
                PositionDTO(
                        teamId = request.teamId,
                        title = request.title,
                        open = request.isOpened,
                        requirements = request.requirements
                )
        )

        return Position.create(result)
    }
}
