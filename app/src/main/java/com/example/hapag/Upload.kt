package com.example.hapag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.hapag.ui.theme.HapagTheme

class Upload : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HapagTheme {
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
            onValueChange = {
                textState = it
            },
            placeholder = {
                Text(
                    text = hint,
                    fontSize = 12.sp
                )
            },
            modifier = modifier
                .size(height = height.dp, width = 400.dp),
            shape = RoundedCornerShape(round.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                unfocusedPlaceholderColor = Color.Gray,
                disabledPlaceholderColor = Color.Gray,
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Black,
            )
        )
    }

    @Composable
    fun Ingredients() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        )
        {
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
    fun BottomNavigationBar() {
        var selectedItem by remember { mutableIntStateOf(0) }
        val items = listOf("Home", "Upload", "My Recipes", "Favorites")
        val icons =
            listOf(Icons.Filled.Home, Icons.Filled.Home, Icons.Filled.Home, Icons.Filled.Home)
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(Color.LightGray)
                            )
                            { Icon(icons[index], contentDescription = item) }
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    },
                    label = { Text(item) },
                    selected = selectedItem == index,
                    onClick = { selectedItem = index }
                )
            }
        }
    }


    @Composable
    fun MyScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            LazyColumn(
                modifier = Modifier
                    .weight(1f)

                    .padding(20.dp)
            ) {
                item {
                    Icon(
                        painter = painterResource(R.drawable.arrow_back),
                        contentDescription = "back icon",
                    )
                }
                item {
                    Row(
                        Modifier.fillMaxWidth()
                    ) {

                        Text(
                            text = "Cancel",
                            color = Color.Red
                        )
                        Spacer(modifier = Modifier.width(80.dp))

                        Text(
                            text = "UPLOAD A RECIPE",
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
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
                            Text(
                                text = "ADD A PHOTO",
                                fontSize = 20.sp
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item {
                    TextBoxWithTitle(
                        "Recipe Title",
                        "Enter the Name of Your Recipe",
                        height = 60,
                        round = 20
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item {
                    TextBoxWithTitle(
                        "Description",
                        "Enter a Unique Description About Your Recipe",
                        height = 60,
                        round = 20
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item {
                    Ingredients()
                }
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item {
                    TextBoxWithTitle("Procedure", "Insert Procedure", height = 52, round = 24)
                }
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item {
                    TextBoxWithTitle(
                        "Notes and Tips",
                        "Something We Need to Know",
                        height = 52,
                        round = 24
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item {
                    TextBoxWithTitle("Serving Size", "Quantity of Serving", height = 52, round = 24)
                }
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item {
                    TextBoxWithTitle(
                        "Preparation Time",
                        "Time Taken in Cooking",
                        height = 52,
                        round = 24
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    )
                    {
                        OutlinedButton(
                            onClick = {
                            },
                            modifier = Modifier.size(height = 40.dp, width = 200.dp),
                            border = BorderStroke(1.dp, Color.Black)
                        )
                        {
                            Text(
                                text = "UPLOAD",
                                color = Color.Black,
                                fontSize = 18.sp
                            )
                        }
                    }
                }

            }
            BottomNavigationBar()
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview2() {
        HapagTheme {
            BottomNavigationBar()
        }
    }
}


