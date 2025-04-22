package com.example.hapag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.hapag.ui.theme.buttonTextColor
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.painterResource
import com.example.hapag.ui.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainActivityScreen()
        }
    }

    @Composable
    @Preview(showBackground = true, showSystemUi = true)
    fun MainActivityScreen() {
        Scaffold(
            bottomBar = {
                NavigationBar(
                    tonalElevation = 0.dp
                ) {
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Home, contentDescription = "Home", modifier = Modifier.size(24.dp)) },
                        label = { Text("Home", fontSize = 10.sp) },
                        selected = true,
                        onClick = { /* TODO: Navigate to Home */ }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Home, contentDescription = "Upload", modifier = Modifier.size(24.dp)) },
                        label = { Text("Upload", fontSize = 10.sp) },
                        selected = false,
                        onClick = { /* TODO: Navigate to Upload */ }
                    )
                    NavigationBarItem(
                        icon = { Icon(painterResource(id = R.drawable.home_icon), contentDescription = "My Recipes", modifier = Modifier.size(24.dp)) }, // Replace with your actual icon
                        label = { Text("My Recipes", fontSize = 10.sp) },
                        selected = false,
                        onClick = { /* TODO: Navigate to My Recipes */ }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.FavoriteBorder, contentDescription = "Favorites", modifier = Modifier.size(24.dp)) },
                        label = { Text("Favorites", fontSize = 10.sp) },
                        selected = false,
                        onClick = { /* TODO: Navigate to Favorites */ }
                    )
                }
            }
        ) { paddingValues ->
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(buttonTextColor)
            ) {
                val dashboard = createRef()

                FigmaDashboardLayout(
                    modifier = Modifier.constrainAs(dashboard) {
                        top.linkTo(parent.top) // Constrain top to the parent's top
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom) // Dashboard fills the remaining space above bottom nav
                    },
                    buttonBackgroundColor = buttonBackgroundColor
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FigmaDashboardLayout(modifier: Modifier = Modifier, buttonBackgroundColor: Color) {
    var searchText by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableStateOf("Sweet") }


    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = searchText,
            onQueryChange = { searchText = it },
            onSearch = { isActive = false },
            active = isActive,
            onActiveChange = { isActive = it },
            placeholder = { Text("Search") }
        ) {}

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Category",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { /* Handle Breakfast click */ },
                colors = ButtonDefaults.buttonColors(containerColor = buttonBackgroundColor)
            ) { Text(text = "Breakfast") }
            Button(
                onClick = { /* Handle Lunch click */ },
                colors = ButtonDefaults.buttonColors(containerColor = buttonBackgroundColor)
            ) { Text(text = "Lunch") }
            Button(
                onClick = { /* Handle Merienda click */ },
                colors = ButtonDefaults.buttonColors(containerColor = buttonBackgroundColor)
            ) { Text(text = "Merienda") }
            Button(
                onClick = { /* Handle Dinner click */ },
                colors = ButtonDefaults.buttonColors(containerColor = buttonBackgroundColor)
            ) { Text(text = "Dinner") }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = { /* Handle Uploaded click */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonBackgroundColor,
                    contentColor = com.example.hapag.ui.theme.buttonTextColor
                ),
                shape = RoundedCornerShape(0.dp)
            ) { Text(text = "Uploaded") }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                modifier = Modifier.weight(1f),
                onClick = { /* Handle Your Favorites click */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonBackgroundColor,
                    contentColor = com.example.hapag.ui.theme.buttonTextColor
                ),
                shape = RoundedCornerShape(0.dp)
            ) { Text(text = "Your Favorites") }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider(color = Color.LightGray, thickness = 3.dp)
        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Sweet",
                color = animateColorAsState(
                    targetValue = if (selectedTab == "Sweet") Color.Black else Color.Gray,
                    animationSpec = tween(durationMillis = 300)
                ).value,
                modifier = Modifier
                    .weight(1f)
                    .clickable { selectedTab = "Sweet" },
                textAlign = TextAlign.Center
            )
            Text(
                text = "Savory",
                color = animateColorAsState(
                    targetValue = if (selectedTab == "Savory") Color.Black else Color.Gray,
                    animationSpec = tween(durationMillis = 300)
                ).value,
                modifier = Modifier
                    .weight(1f)
                    .clickable { selectedTab = "Savory" },
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Divider(color = Color.LightGray, thickness = 3.dp)
        Spacer(modifier = Modifier.height(20.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            repeat(5) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    repeat(2) {
                        RecipeCard()
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        Divider(color = Color.LightGray, thickness = 1.dp)
    }
}

@Composable
fun RoundedCornerShape(x0: Dp) {
    androidx.compose.foundation.shape.RoundedCornerShape(x0)
}

@Composable
fun RecipeCard() {
    Column(
        modifier = Modifier.width(170.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .size(170.dp, 100.dp)
                .background(Color.LightGray)
        ) {
            // Image placeholder
        }
        Text(text = "Recipe Title", fontWeight = FontWeight.Bold)
        Text(text = "Food Type")
    }
}

@Composable
fun MainActivityScreenPreview() {
    MainActivity().MainActivityScreen()
}