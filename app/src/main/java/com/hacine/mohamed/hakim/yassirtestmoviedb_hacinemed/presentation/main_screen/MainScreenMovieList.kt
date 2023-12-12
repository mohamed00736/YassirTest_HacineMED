package com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.hacine.mohamed.hakim.myapplication.presentation.UiState
import com.hacine.mohamed.hakim.yassir_test_moviedb.ui.components.FailedView
import com.hacine.mohamed.hakim.yassir_test_moviedb.ui.components.MovieListItem
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.R
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.data.models.MovieList
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.ui.components.LoadingView


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onMovieSelected: (movieid: String) -> Unit
) {

    val uiState = viewModel.movieListuiState.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.trending)) },

            modifier = Modifier.shadow(elevation = 16.dp)
        )

        when (uiState.value) {

            is UiState.Loading -> {
                LoadingView()
            }

            is UiState.Success -> {
                ((uiState.value as UiState.Success).data as MovieList).let { movieList->
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(horizontal = 0.dp)
                    ) {
                        items(movieList.results) { movie ->

                            MovieListItem(movie = movie) {

                                onMovieSelected(movie.id.toString())

                            }

                        }
                    }
                }
            }

            is UiState.Fail -> {
                (uiState.value as UiState.Fail).message?.let {
                    FailedView(
                        onRetry = viewModel::getTrendingMovies,
                        retryable = true,
                        errorText = it
                    )
                }
            }

            else -> Unit
        }

    }


}