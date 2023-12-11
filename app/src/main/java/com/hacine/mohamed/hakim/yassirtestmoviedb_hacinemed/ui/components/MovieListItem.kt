package com.hacine.mohamed.hakim.yassir_test_moviedb.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.R
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.data.models.Movie
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.data.network.Constants

import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.ui.components.ThreeDotLoading

@Composable
fun MovieListItem(movie: Movie, onMovieSelected: () -> Unit) {

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clickable { onMovieSelected() }

    ) {
        SubcomposeAsyncImage(
            model = Constants.Image_baseURL + movie.posterPath,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            loading = {

                    ThreeDotLoading()

            },
            error = {
                Image(
                    painter = painterResource(id = R.drawable.baseline_error_outline_24),
                    contentDescription = "",
                    modifier = Modifier.size(50.dp)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(233.dp)
                .then(
                    Modifier.shadow(
                        elevation = 12.dp,
                        shape = RoundedCornerShape(8.dp),
                        clip = true,
                        ambientColor = Color.Black.copy(1f)
                    )

                )
                .clip(
                    shape = RoundedCornerShape(8.dp)
                )

        )

        Spacer(modifier = Modifier.height(8.dp))

        // Displaying movie title and release date
        BasicText(
            text = movie.title ?: "",
            style = TextStyle(
                color = MaterialTheme.colorScheme.secondary,
                fontStyle = MaterialTheme.typography.labelMedium.fontStyle
            ),
            overflow = TextOverflow.Visible,
            modifier = Modifier.wrapContentWidth()
        )
        Column(verticalArrangement = Arrangement.SpaceAround) {
            RatingBar(rating = (movie.voteAverage?.div(2))?.toDouble() ?: 0.0, stars = 5)
            Spacer(modifier = Modifier.height(4.dp))
            BasicText(
                text = "(${(movie.voteAverage?.div(2))?.toDouble()})" ?: "",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontStyle = MaterialTheme.typography.labelMedium.fontStyle
                ),
                overflow = TextOverflow.Visible,
                modifier = Modifier.wrapContentWidth()
            )
        }

        Row(horizontalArrangement = Arrangement.Start) {
            BasicText(
                text = "${movie.releaseDate}" ?: "",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontStyle = MaterialTheme.typography.labelMedium.fontStyle
                ),
                overflow = TextOverflow.Visible,
                modifier = Modifier.wrapContentWidth(),

                )
            Spacer(modifier = Modifier.width(16.dp))

        }

    }


}
