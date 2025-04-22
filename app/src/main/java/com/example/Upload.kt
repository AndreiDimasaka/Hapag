
package com.example

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapag.R
import com.example.hapag.buttonBackgroundColor
import com.example.hapag.buttonTextColor

class Upload : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            com.example.hapag.ui.theme.HapagTheme {
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
    fun OverlayButton(onAddClick : () -> Unit, text: String) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text(
                text = text,
                style = TextStyle(color = Color.Black, fontSize = 20.sp),
                modifier = Modifier.padding(end = 220.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = Color.Transparent,
                elevation = FloatingActionButtonDefaults.elevation(0.dp)

            ) {
                Icon(
                    painter = painterResource(R.drawable.add_circle),
                    contentDescription = "add icon",
                    modifier = Modifier.size(80.dp)
                )
            }
        }

        }
    }

    @Composable
    fun InputList(onClose: () -> Unit, text : String) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f)),
            contentAlignment = Alignment.Center
        ) {
        Card(
            colors = CardDefaults.cardColors(Color.White),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier
                .padding(bottom = 180.dp, start = 30.dp, end = 30.dp, top = 100.dp)
        )
        {
            Column(
                modifier = Modifier
                    .padding(top = 10.dp, start = 5.dp, end = 5.dp)
            )
            {
                IconButton(onClick = onClose) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_back),
                        contentDescription = "return",
                        modifier = Modifier.padding(start = 10.dp)
                            .size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                        .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(20.dp))
                        .height(50.dp)

                ) {
                    Text(
                        text = text,
                        fontSize = 15.sp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FloatingActionButton(
                        onClick = { },
                        containerColor = Color.White,
                        elevation = FloatingActionButtonDefaults.elevation(0.dp),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.add_circle),
                            contentDescription = "Add Ingredient",
                            modifier = Modifier.size(40.dp)
                        )
                    }

                }
            }
        }
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
                    .background(Color.White),
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(Color.Transparent)
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

        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun TopReturnBar() {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Navigate back */ }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = buttonBackgroundColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = buttonTextColor)
            )
        }


        @Composable
        fun MyScreen() {
            var showIngredientInput by remember {mutableStateOf(false)}
            var showProcedureInput by remember {mutableStateOf(false)}

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = { BottomNavigationBar() },
            ) { innerPadding ->
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
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
                                    .padding(
                                        start = 50.dp,
                                        top = 20.dp,
                                        bottom = 20.dp,
                                        end = 50.dp
                                    ),
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
                        OverlayButton(onAddClick = { showIngredientInput= true }, text = "Ingredients used")
                    }
                    item {
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                    item {
                        OverlayButton(onAddClick = { showProcedureInput = true }, text = "Procedure")
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
                        TextBoxWithTitle(
                            "Serving Size",
                            "Quantity of Serving",
                            height = 52,
                            round = 24
                        )
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
            }

            if (showIngredientInput)
            { InputList(onClose = {showIngredientInput = false}, text = "Insert Ingredient and Measurement(Optional)") }
            if (showProcedureInput)
            { InputList(onClose = {showProcedureInput = false}, text = "Insert Steps or Procedures") }
        }


        @Preview(showBackground = true)
        @Composable
        fun GreetingPreview2() {
            com.example.hapag.ui.theme.HapagTheme {
                MyScreen()
            }
        }
}