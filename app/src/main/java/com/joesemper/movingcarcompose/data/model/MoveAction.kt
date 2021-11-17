package com.joesemper.movingcarcompose.data.model

sealed class MoveAction {
    class Rotate(val deg: Float): MoveAction()
    class Move(val position: Position): MoveAction()
}