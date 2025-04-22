package com.example.hapag.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapag.buttonBackgroundColor
import androidx.compose.ui.res.painterResource
import com.example.hapag.R
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun BottomNavigationBar(onItemSelected: (Int) -> Unit, selectedIndex: Int = 0) {
    var selectedItem by remember { mutableIntStateOf(selectedIndex) }
    val items = listOf("Home", "Upload", "My Recipes", "Favorites")
    val icons =
        listOf(Icons.Filled.Home, painterResource(id = R.drawable.baseline_file_upload_24), painterResource(id = R.drawable.baseline_menu_book_24), Icons.Filled.Favorite)
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White),
        tonalElevation = 0.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(Color.Transparent)
                        ) {
                            val icon = icons[index]
                            if (icon is ImageVector) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = item,
                                    tint = if (selectedItem == index) buttonBackgroundColor else Color.Gray
                                )
                            } else if (icon is Painter) {
                                Icon(
                                    painter = icon,
                                    contentDescription = item,
                                    tint = if (selectedItem == index) buttonBackgroundColor else Color.Gray
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                },
                label = { Text(item, color = if (selectedItem == index) buttonBackgroundColor else Color.Gray, fontSize = 10.sp) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    onItemSelected(index)
                }
            )
        }
    }
}