package com.example.hapag.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapag.R
import androidx.compose.ui.layout.ContentScale


@Composable
@Preview(showBackground = true)
fun IntroScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1EDE7))
    ) {
        Image(
            painter = painterResource(id = R.drawable.background2),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(), // Make the image fill the Box
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        { Spacer(modifier = Modifier.height(30.dp))

            Image(
                painter = painterResource(id = R.drawable.handa),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 190.dp)
                    .height(300.dp)
            )
        }
    }
}

