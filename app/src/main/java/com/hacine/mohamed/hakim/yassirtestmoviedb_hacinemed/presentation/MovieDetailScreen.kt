package com.hacine.mohamed.hakim.yassir_test_moviedb.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.hacine.mohamed.hakim.myapplication.presentation.UiState

import com.hacine.mohamed.hakim.yassir_test_moviedb.ui.components.FailedView
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.ui.components.LoadingView
import com.hacine.mohamed.hakim.yassir_test_moviedb.ui.components.RatingBar
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.R
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.data.models.Movie
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.data.network.Constants
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.ui.components.ThreeDotLoading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(movieid: String , viewModel: MainViewModel = hiltViewModel(), onBack:()->Unit ) {


    LaunchedEffect(key1 = true){
        viewModel.getMovieById(movieid)
    }

    val uiState = viewModel.movieDetailuiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopAppBar(
            title = { Text(text = "Details") },
            navigationIcon = {
                IconButton(onClick = {
                    onBack()
                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            modifier = Modifier.shadow(elevation = 16.dp)
        )

        when(uiState.value){
            is UiState.Loading ->{
                LoadingView()
            }
            is UiState.Success ->{
                ( (uiState.value as UiState.Success).data as Movie).let { movie->

                    MovieDetails(movie = movie)
                }


            }
            is UiState.Fail -> {
                (uiState.value as UiState.Fail).message?.let {
                    FailedView(onRetry = {
                        viewModel.getTrendingMovies()
                    }, retryable = true, errorText = it)
                }
            }

            else -> {}
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun MovieDetails(movie: Movie) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            DetailHeader(movie = movie)
        }
        item {
            movie.title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RatingBar(rating = (movie.voteAverage?.div(2))?.toDouble() ?: 0.0, stars = 5,)
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Votes : ${ movie.voteCount.toString() }",
                    style = MaterialTheme.typography.bodyMedium,

                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 12.dp)
            ) {
                Icon(Icons.Default.DateRange, contentDescription = null)
                Spacer(modifier = Modifier.width(4.dp))
                movie.releaseDate?.let { Text(text = it) }
            }
        }
        item {
            movie.overview?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }
}


@Composable
private fun DetailHeader(movie: Movie) {
    ConstraintLayout {
        val (banner, avatar) = createRefs()

        SubcomposeAsyncImage(
            model = Constants.Image_baseURL + movie.backdropPath,
            contentDescription = null,
            loading = {
                ThreeDotLoading()
            },
            error = {

                Image(
                    painter = painterResource(id = R.drawable.baseline_error_outline_24),
                    contentDescription = ""
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .constrainAs(banner) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentScale = ContentScale.FillBounds

        )

        SubcomposeAsyncImage(
            model = Constants.Image_baseURL + movie.posterPath,
            contentDescription = null,
            loading = {
                ThreeDotLoading()
            },
            error = {
                Image(
                    painter = painterResource(id = R.drawable.baseline_error_outline_24),
                    contentDescription = ""
                )
            },
            modifier = Modifier
                .size(150.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .border(
                    BorderStroke(
                        4.dp,
                        color = if (isSystemInDarkTheme()) Color.Black.copy(0.6f) else Color.White.copy(
                            0.2f
                        )
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                .then(
                    Modifier.shadow(16.dp)
                )

                .constrainAs(avatar) {
                    top.linkTo(banner.bottom, margin = -75.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
                ,
            contentScale = ContentScale.FillBounds

        )


    }
}