package com.example.hapag.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapag.R
import com.example.hapag.ui.theme.HapagTheme

class Upload : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HapagTheme {
                MyScreen() // Display MyScreen when the activity is created
            }
        }
    }

    @Composable
    fun TextBoxWithTitle(
        title: String,
        hint: String,
        modifier: Modifier = Modifier,
        height: Int,
        round: Int
    ) {
        var textState by remember { mutableStateOf(TextFieldValue()) }

        Text(
            text = title,
            style = TextStyle(color = Color.Black, fontSize = 20.sp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = textState,
            onValueChange = { textState = it },
            placeholder = { Text(text = hint, fontSize = 12.sp) },
            modifier = modifier.size(height = height.dp, width = 400.dp),
            shape = RoundedCornerShape(round.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                unfocusedPlaceholderColor = Color.Gray,
                disabledPlaceholderColor = Color.Gray,
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Black
            )
        )
    }

    @Composable
    fun Ingredients() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Ingredients Used",
                style = TextStyle(color = Color.Black, fontSize = 20.sp),
                modifier = Modifier.padding(end = 220.dp)
            )

            Icon(
                painter = painterResource(R.drawable.add_circle),
                contentDescription = "add icon",
                modifier = Modifier.size(80.dp)
            )
        }
    }

    @Composable
    fun MyScreen() {
        var selectedBottomNavItemIndex by remember { mutableIntStateOf(1) } // Set initial selection to "Upload" (index 1)

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomNavigationBar(
                    onItemSelected = { index ->
                        selectedBottomNavItemIndex = index
                        // You can add navigation logic here based on the selected index
                        println("Bottom navigation item selected: $index")
                    },
                    selectedIndex = selectedBottomNavItemIndex
                )
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                item {
                    Icon(
                        painter = painterResource(R.drawable.arrow_back),
                        contentDescription = "back icon"
                    )
                }

                item {
                    Row(Modifier.fillMaxWidth()) {
                        Text(text = "Cancel", color = Color.Red)
                        Spacer(modifier = Modifier.width(80.dp))
                        Text(text = "UPLOAD A RECIPE")
                    }
                }

                item { Spacer(modifier = Modifier.height(10.dp)) }

                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .border(width = 1.dp, color = Color.Black, shape = RectangleShape)
                            .size(height = 120.dp, width = 400.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 50.dp, top = 20.dp, bottom = 20.dp, end = 50.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(R.drawable.lightgray),
                                contentDescription = "upload icon"
                            )
                            Spacer(modifier = Modifier.width(50.dp))
                            Text(text = "ADD A PHOTO", fontSize = 20.sp)
                        }
                    }
                }

                item { Spacer(modifier = Modifier.height(15.dp)) }

                item {
                    TextBoxWithTitle(
                        "Recipe Title",
                        "Enter the Name of Your Recipe",
                        height = 60,
                        round = 20
                    )
                }

                item { Spacer(modifier = Modifier.height(15.dp)) }

                item {
                    TextBoxWithTitle(
                        "Description",
                        "Enter a Unique Description About Your Recipe",
                        height = 60,
                        round = 20
                    )
                }

                item { Spacer(modifier = Modifier.height(15.dp)) }

                item { Ingredients() }

                item { Spacer(modifier = Modifier.height(15.dp)) }

                item {
                    TextBoxWithTitle("Procedure", "Insert Procedure", height = 52, round = 24)
                }

                item { Spacer(modifier = Modifier.height(15.dp)) }

                item {
                    TextBoxWithTitle(
                        "Notes and Tips",
                        "Something We Need to Know",
                        height = 52,
                        round = 24
                    )
                }

                item { Spacer(modifier = Modifier.height(15.dp)) }

                item {
                    TextBoxWithTitle("Serving Size", "Quantity of Serving", height = 52, round = 24)
                }

                item { Spacer(modifier = Modifier.height(15.dp)) }

                item {
                    TextBoxWithTitle(
                        "Preparation Time",
                        "Time Taken in Cooking",
                        height = 52,
                        round = 24
                    )
                }

                item { Spacer(modifier = Modifier.height(15.dp)) }

                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = { },
                            modifier = Modifier.size(height = 40.dp, width = 200.dp),
                            border = BorderStroke(1.dp, Color.Black)
                        ) {
                            Text(
                                text = "UPLOAD",
                                color = Color.Black,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview2() {
        HapagTheme {
            MyScreen()
        }
    }
}