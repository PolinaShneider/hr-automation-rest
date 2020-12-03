package com.shneider.hrautomation.service.position

import com.shneider.hrautomation.data.position.Position
import com.shneider.hrautomation.request.PositionRequest


interface PositionService {
    fun listAllPositions(): List<Position>

    fun listOpenPositions(): List<Position>

    fun getPositionById(id: String): Position

    fun savePosition(request: PositionRequest): Position

    fun updatePosition(positionId: String, request: PositionRequest)
}
