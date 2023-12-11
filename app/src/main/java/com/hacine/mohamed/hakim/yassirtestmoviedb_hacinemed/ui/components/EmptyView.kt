package com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.R


@Preview
@Composable
fun EmptyView(
    modifier: Modifier = Modifier,
    message: String="NOTHING TO SHOW",
    color: Color = Color.White,
){
    Column( modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            painter = painterResource(id = R.drawable.nothing),
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = message,
            color = color,
            fontSize = 16.sp,
            fontWeight = FontWeight.W400,
        )
    }
}


