package com.joesemper.movingcarcompose.ui.viewmodel


import androidx.lifecycle.ViewModel
import com.joesemper.movingcarcompose.data.model.Position
import com.joesemper.movingcarcompose.data.repository.RouteRepository

class MainViewModel(private val repository: RouteRepository): ViewModel() {

    fun newRoute(start: Position, finish: Position) = repository.buildRoute(start, finish)
}