package com.example.hapag.composables.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapag.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopReturnBar(
    title : String,
    onClick: () -> Unit = {}
) {
    TopAppBar(
        navigationIcon = {
            if (title == "Search") {
                IconButton(
                    onClick = { onClick() },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = AppTheme.colorScheme.secondary,
                        contentColor = AppTheme.colorScheme.onTertiary
                    )
                ) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.height(32.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppTheme.colorScheme.secondary,
                    navigationIconContentColor = AppTheme.colorScheme.onTertiary,
            titleContentColor = AppTheme.colorScheme.onTertiary
        ),
        modifier = Modifier.height(80.dp),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 10.dp,
                        end = 0.dp,
                    )

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


