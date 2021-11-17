package com.joesemper.movingcarcompose.data.repository

import com.joesemper.movingcarcompose.data.model.MoveAction
import com.joesemper.movingcarcompose.data.model.Position
import kotlinx.coroutines.flow.Flow


interface RouteRepository {

    fun buildRoute(start: Position, finish: Position): Flow<MoveAction>
}