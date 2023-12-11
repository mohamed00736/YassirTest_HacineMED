package com.hacine.mohamed.hakim.yassir_test_moviedb.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToNextScreen: () -> Unit
) {
    LoadingSplash()



    LaunchedEffect(Unit) {
        delay(2500)
        navigateToNextScreen()
    }
}

@Composable
private fun LoadingSplash() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottimovie))
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .fillMaxHeight()
            .background(if (isSystemInDarkTheme() )Color.Black else Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            speed = 0.9f,
            iterations = Int.MAX_VALUE,
            contentScale = ContentScale.Inside,
            modifier = Modifier.wrapContentSize()
        )
    }

}