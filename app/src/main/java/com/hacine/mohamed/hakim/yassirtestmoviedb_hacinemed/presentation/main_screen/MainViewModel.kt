package com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.presentation.main_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.hacine.mohamed.hakim.myapplication.presentation.UiState
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.data.network.Resource
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {

    private val _movieListuiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Idle)
    val movieListuiState: StateFlow<UiState> get() = _movieListuiState


    init {
        getTrendingMovies()
    }


     fun getTrendingMovies() {
        viewModelScope.launch {

            _movieListuiState.value = UiState.Loading
            when (val response = moviesRepository.getTrendingMovies()) {
                is Resource.Success -> {
                    _movieListuiState.value = UiState.Success(data = response.value)
                }

                is Resource.Failure -> {
                    _movieListuiState.value = UiState.Fail(message = response.message)
                }
            }

        }

    }

}