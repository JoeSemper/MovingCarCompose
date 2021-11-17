package com.joesemper.movingcarcompose.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.joesemper.movingcarcompose.R

@Composable
fun CarView(
    animatedOffset: IntOffset
) {
    Box(
        Modifier
            .offset {
                animatedOffset
            }
            .size(42.dp)
    ) {
        Icon(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_car),
            contentDescription = "Car"
        )
    }
}
