package com.example.movieappactivity.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappactivity.data.DataSource
import com.example.movieappactivity.model.Movie
import com.example.movieappactivity.repository.MovieDBContainer
import kotlinx.coroutines.launch

sealed interface ListMovieUIState { // Creating an Interface
    data class Success(val data: List<Movie>) : ListMovieUIState
    object Error : ListMovieUIState
    object Loading : ListMovieUIState
}

class ListMovieViewModel :
    ViewModel() {// Create the function when the data from the view has to be manipulated logically
    var listMovieUIState: ListMovieUIState by mutableStateOf(ListMovieUIState.Loading)
        private set // Only ListMovieUIState that enables to modify anything, the others wll only be the listeners
    // Only the set function is private, whereas the get function may be punblic
    private lateinit var data: List<Movie>

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch { // Coroutine enables us to regulate the data and run the app simultaneously
            try {
                data = MovieDBContainer().movieDBRepository.getAllMovie(1)
                listMovieUIState = ListMovieUIState.Success(data)
            } catch (e: Exception) {
                listMovieUIState = ListMovieUIState.Error
            }
        }
    }

    fun onFavClicked(movie: Movie) {
        movie.isLiked = !movie.isLiked
        // Send the update result to server
    }
}