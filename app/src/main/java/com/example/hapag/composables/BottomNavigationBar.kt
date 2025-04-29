package com.example.hapag.ui

import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapag.MainActivity
import com.example.hapag.MyFavoritesActivity
import com.example.hapag.MyRecipesActivity
import androidx.compose.ui.res.painterResource
import com.example.hapag.R
import androidx.compose.ui.graphics.painter.Painter
import com.example.hapag.ui.Front.Upload
import com.example.hapag.ui.theme.AppTheme
import com.example.hapag.ui.theme.LeafyGreen
import com.example.hapag.ui.theme.SandyBeige

@Composable
fun BottomNavigationBar(onItemSelected: (Int) -> Unit, selectedIndex: Int = 0) {
    var selectedItem by remember { mutableIntStateOf(selectedIndex) }
    val items = listOf("Home", "Upload", "My Recipes", "Favorites")
    val icons =
        listOf(Icons.Filled.Home, painterResource(id = R.drawable.baseline_file_upload_24), painterResource(id = R.drawable.baseline_menu_book_24), Icons.Filled.Favorite)
    val context = LocalContext.current

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        tonalElevation = 0.dp,
        containerColor = AppTheme.colorScheme.secondary,
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                        ) {
                            val icon = icons[index]
                            if (icon is ImageVector) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = item,
                                    tint = AppTheme.colorScheme.onTertiary
                                )
                            } else if (icon is Painter) {
                                Icon(
                                    painter = icon,
                                    contentDescription = item,
                                    tint = AppTheme.colorScheme.onTertiary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                },
                label = { Text (
                    item, color = if (selectedItem == index) AppTheme.colorScheme.onTertiary else Color.Gray,
                    fontSize = 10.sp,
                    style = AppTheme.typography.labelSmall
                )
                        },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    onItemSelected(index)
                    when (index) {
                        0 -> { // Index 0 corresponds to "Home" - You might want to navigate somewhere specific
                            // Example:
                            val intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                        }
                        1 -> { // Index 1 corresponds to "Upload"
                            val intent = Intent(context, Upload::class.java)
                            context.startActivity(intent)
                        }
                        2 -> { // Index 2 corresponds to "My Recipes"
                            val intent = Intent(context, MyRecipesActivity::class.java)
                            context.startActivity(intent)
                        }
                        3 -> { // Index 3 corresponds to "Favorites"
                            val intent = Intent(context, MyFavoritesActivity::class.java)
                            context.startActivity(intent)
                        }
                    }
                }
            )
        }
    }
}