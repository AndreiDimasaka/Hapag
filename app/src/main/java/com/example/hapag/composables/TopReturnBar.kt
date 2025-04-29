 package com.example.hapag.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapag.R
import com.example.hapag.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopReturnBar(title : String) {
        androidx.compose.material3.TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = AppTheme.colorScheme.secondary,
                        navigationIconContentColor = AppTheme.colorScheme.onTertiary,
                titleContentColor = AppTheme.colorScheme.onTertiary
            ),
            modifier = Modifier.height(80.dp),
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "Back",
                        modifier = Modifier.size(30.dp)
                    )
                }
            },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 40.dp, top = 10.dp)
                ) {
                    Text(
                        text = title,
                        style = AppTheme.typography.titleLarge,
                    )
                }
            }
        )
    }
@Preview(showBackground = true)
@Composable
fun TopReturnBarPreview(){
    AppTheme {
        TopReturnBar(title = "Upload A Recipe")
    }
}


