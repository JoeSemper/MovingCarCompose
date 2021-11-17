package com.joesemper.movingcarcompose.data.datasource

import com.joesemper.movingcarcompose.data.model.MoveAction
import com.joesemper.movingcarcompose.data.model.Position
import com.joesemper.movingcarcompose.data.repository.RouteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow


class RouteRepositoryImpl: RouteRepository {

    override fun buildRoute(start: Position, finish: Position): Flow<MoveAction> {

        val turnA = MoveAction.Rotate(deg = calcTurnNotEven(start, finish))
        val pointA = MoveAction.Move(
            Position(
                x = (finish.x + start.x)/2,
                y = start.y
            )
        )

        val turnB = MoveAction.Rotate(deg = calcTurnEven(start, finish))
        val pointB = MoveAction.Move(
            Position(
                x = (finish.x + start.x)/2,
                y = (finish.y + start.y)/2
            )
        )

        val turnC = MoveAction.Rotate(deg = calcTurnNotEven(start, finish))
        val pointC = MoveAction.Move(
            Position(
                x = finish.x,
                y = (finish.y + start.y)/2
            )
        )

        val turnD = MoveAction.Rotate(deg = calcTurnEven(start, finish))
        val pointD = MoveAction.Move(
            finish
        )

        return listOf(turnA, pointA, turnB, pointB, turnC, pointC, turnD, pointD).asFlow()
    }

    private fun calcTurnNotEven(start: Position, finish: Position) = when {
        start.x > finish.x && start.y < finish.y -> 270f
        start.x > finish.x && start.y > finish.y -> -90f
        start.x < finish.x && start.y > finish.y -> 90f
        start.x < finish.x && start.y < finish.y -> -270f
        else -> 0f
    }

    private fun calcTurnEven(start: Position, finish: Position) = when {
        start.y > finish.y && start.x < finish.x -> -0f
        start.y > finish.y && start.x > finish.x -> 0f
        start.y < finish.y && start.x < finish.x -> -180f
        start.y < finish.y && start.x > finish.x -> 180f
        else -> 0f
    }
}