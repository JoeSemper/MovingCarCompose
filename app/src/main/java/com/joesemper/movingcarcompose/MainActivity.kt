package com.joesemper.movingcarcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.joesemper.movingcarcompose.ui.compose.AnimatableExample
import com.joesemper.movingcarcompose.ui.theme.MovingCarComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovingCarComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AnimatableExample()
                }
            }
        }
    }
}
