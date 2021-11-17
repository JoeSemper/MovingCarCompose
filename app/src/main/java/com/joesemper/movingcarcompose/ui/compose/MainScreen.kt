package com.joesemper.movingcarcompose.ui.compose

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.joesemper.movingcarcompose.data.model.MoveAction
import com.joesemper.movingcarcompose.data.model.Position
import com.joesemper.movingcarcompose.ui.viewmodel.MainViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = getViewModel()

    val animatedOffset = remember { Animatable(Offset(0f, 0f), Offset.VectorConverter) }

    val carRotation = remember {
        mutableStateOf(0f)
    }

    val rotation = animateFloatAsState(
        targetValue = carRotation.value,
        animationSpec = spring()
    )

    val lastTapPosition = remember {
        mutableStateOf<Offset?>(null)
    }

    LaunchedEffect(lastTapPosition.value) {
        lastTapPosition.value?.let { tapPosition ->
            viewModel
                .newRoute(
                    start = Position(
                        animatedOffset.value.x,
                        animatedOffset.value.y
                    ),
                    finish = Position(tapPosition.x, tapPosition.y)
                )
                .collect { moveAction ->
                    when (moveAction) {
                        is MoveAction.Move -> {
                            animatedOffset.animateTo(
                                Offset(
                                    moveAction.position.x,
                                    moveAction.position.y
                                ),
                                animationSpec = spring(stiffness = Spring.StiffnessLow)
                            )
                        }
                        is MoveAction.Rotate -> {
                            carRotation.value = moveAction.deg
                        }
                    }
                }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)
        .pointerInput(Unit) {
            coroutineScope {
                while (true) {
                    awaitPointerEventScope {
                        lastTapPosition.value = awaitFirstDown().position
                    }
                }
            }
        }
    ) {
        Text("Tap anywhere", Modifier.padding(8.dp).align(Alignment.TopCenter))

        CarView(
            animatedOffset = IntOffset(
                animatedOffset.value.x.toInt(),
                animatedOffset.value.y.toInt()
            ),
            rotationDeg = rotation.value
        )
    }
}