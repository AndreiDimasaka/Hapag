package com.example.hapag.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.hapag.Composables
import com.example.hapag.MainActivity
import com.example.hapag.R
import com.example.hapag.ui.theme.HapagTheme
import com.example.ui.theme.DarkBrown
import com.example.ui.theme.Light

val buttonTextColor = Color(0xFFF1EDE7)
val buttonBackgroundColor = Color(0xFF403A35)

class Upload : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HapagTheme {
                MyScreen()
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
            style = TextStyle(color = buttonBackgroundColor, fontSize = 20.sp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = textState,
            onValueChange = { textState = it },
            placeholder = { Text(text = hint, fontSize = 12.sp, color = Color.Gray) },
            modifier = modifier.size(height = height.dp, width = 400.dp),
            shape = RoundedCornerShape(round.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = buttonBackgroundColor,
                unfocusedTextColor = buttonBackgroundColor,
                unfocusedPlaceholderColor = Color.Gray,
                disabledPlaceholderColor = Color.Gray,
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = buttonBackgroundColor
            )
        )
    }
    @Composable
    fun ImageSelectnDisplay() {
        var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
        val singleImagePicker = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> selectedImageUri = uri }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(model = selectedImageUri,
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .padding(start = 8.dp),
                contentScale = ContentScale.Crop)
            Spacer(Modifier.width(60.dp))
            OutlinedButton(
                onClick = {singleImagePicker.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                ) },){
                Text (
                    text = "ADD IMAGE",
                    color = DarkBrown,
                    fontSize = 18.sp
                )
            }
        }
    }

    @Composable
    fun Ingredients() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Ingredients Used",
                style = TextStyle(color = buttonBackgroundColor, fontSize = 20.sp),
                modifier = Modifier.padding(end = 220.dp)
            )

            Icon(
                painter = painterResource(R.drawable.add_circle),
                contentDescription = "add icon",
                modifier = Modifier.size(80.dp),
                tint = buttonBackgroundColor // Apply tint to the icon
            )
        }
    }

    @Composable
    fun MyScreen() {
        var showIngredientInput by remember { mutableStateOf(false) }
        var showProcedureInput by remember { mutableStateOf(false) }
        val backgroundColor = buttonTextColor
        val context = LocalContext.current

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomNavigationBar(
                    onItemSelected = { index ->
                        println("Bottom navigation item selected in Upload: $index")
                    },
                    selectedIndex = 1 // Highlight "Upload" (assuming it's at index 1)
                )
            },
            containerColor = backgroundColor
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                item {
                    Column {
                        Icon(
                            painter = painterResource(R.drawable.arrow_back),
                            contentDescription = "back icon",
                            tint = buttonBackgroundColor,
                            modifier = Modifier.clickable {
                                val intent = Intent(context, MainActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                                context.startActivity(intent)
                            }
                        )

                        Row(Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center) {
                            Text(text = "UPLOAD A RECIPE", color = buttonBackgroundColor)
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = buttonBackgroundColor,
                                    shape = RectangleShape
                                )
                                .size(height = 120.dp, width = 400.dp)
                                .background(Light) // Added a white background for the box
                        ) {
                            ImageSelectnDisplay()
                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        TextBoxWithTitle(
                            "Recipe Title",
                            "Enter the Name of Your Recipe",
                            height = 60,
                            round = 20
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        TextBoxWithTitle(
                            "Description",
                            "Enter a Unique Description About Your Recipe",
                            height = 60,
                            round = 20
                        )

                        Spacer(modifier = Modifier.height(15.dp))


                        Composables().OverlayButton(
                            onAddClick = { showIngredientInput = true },
                            text = "Ingredients used")

                        Spacer(modifier = Modifier.height(15.dp))

                        Composables().OverlayButton(
                            onAddClick = { showProcedureInput = true },
                            text = "Procedure"
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        TextBoxWithTitle(
                            "Notes and Tips",
                            "Something We Need to Know",
                            height = 52,
                            round = 24
                        )
                        Spacer(modifier = Modifier.height(15.dp))

                        TextBoxWithTitle(
                            "Serving Size",
                            "Quantity of Serving",
                            height = 52,
                            round = 24
                        )
                        Spacer(modifier = Modifier.height(15.dp))

                        TextBoxWithTitle(
                            "Preparation Time",
                            "Time Taken in Cooking",
                            height = 52,
                            round = 24
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedButton(
                                onClick = { },
                                modifier = Modifier.size(height = 40.dp, width = 200.dp),
                                border = BorderStroke(1.dp, buttonBackgroundColor),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = buttonBackgroundColor)
                            ) {
                                Text(
                                    text = "UPLOAD",
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
            }
        }
        if (showIngredientInput) {
            Composables().IngredientInputList(onClose = { showIngredientInput = false })
        }
        if (showProcedureInput) {
            Composables().ProcedureInputList(onClose = { showProcedureInput = false })
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