package com.example.movieappactivity.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappactivity.data.DataSource
import com.example.movieappactivity.model.Movie
import kotlinx.coroutines.launch

sealed interface MovieDetailUIState {
    data class Success(val data: Movie) : MovieDetailUIState
    object Error : MovieDetailUIState
    object Loading : MovieDetailUIState
}

class MovieDetailViewModel : ViewModel() {

    private lateinit var data: Movie
    var movieDetailUIState: MovieDetailUIState by mutableStateOf(MovieDetailUIState.Loading)
        private set

    fun getMovieByID(id: Int){
        viewModelScope.launch {
            for (temp in DataSource().loadMovie()){
                if (temp.id == id){
                    data = temp
                    break
                }
            }
            movieDetailUIState = MovieDetailUIState.Success(data)
        }
    }
}