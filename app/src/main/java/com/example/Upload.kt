
package com.example

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapag.R
import kotlin.collections.toMutableList

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
    fun OverlayButton(onAddClick: () -> Unit, text: String) {
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
    fun IngredientInputList(onClose: () -> Unit) {
        var ingredientText by remember { mutableStateOf("") }
        var measurementText by remember { mutableStateOf("") }
        var ingredientList by remember { mutableStateOf<List<Pair<String, String>>>(emptyList()) }
        val context = LocalContext.current

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
                    .padding(bottom = 180.dp, start = 20.dp, end = 20.dp, top = 100.dp)
                    .fillMaxSize()
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
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth().padding(8.dp)
                                .padding(horizontal = 16.dp)
                        ) {
                            Text(
                                text = "Required",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(end = 70.dp)
                            )
                            Text(
                                text = "Optional",
                                modifier = Modifier.padding(end = 10.dp)
                            )

                        }
                        Spacer(modifier = Modifier.height(5.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                                .padding(horizontal = 16.dp)


                        ) {
                            OutlinedTextField(
                                value = ingredientText,
                                onValueChange = { ingredientText = it },
                                label = { Text(text = "Ingredient") },
                                singleLine = true,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 8.dp)


                            )
                            OutlinedTextField(
                                value = measurementText,
                                onValueChange = { measurementText = it },
                                label = { Text(text = "Measurement") },
                                singleLine = true,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 8.dp, end = 8.dp)
                            )


                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            FloatingActionButton(
                                onClick = {
                                    val ingredient = ingredientText
                                    val measurement = measurementText

                                    if (ingredient.isBlank()) {
                                        Toast.makeText(
                                            context,
                                            "Please enter an ingredient",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        ingredientList =
                                            ingredientList + (ingredient to measurement)

                                        ingredientText = ""
                                        measurementText = ""
                                    }
                                },
                                containerColor = Color.White,
                                elevation = FloatingActionButtonDefaults.elevation(0.dp),
                            ) { Icon(
                                    painter = painterResource(R.drawable.add_circle),
                                    contentDescription = "Add Ingredient",
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                        Spacer(Modifier.height(20.dp))
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth()
                            ) {

                                items(ingredientList) { item ->
                                    val (ingredient, measurement) = item
                                    if (measurement.isNotBlank()) {
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceEvenly,
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .padding(start =30.dp, end = 30.dp)
                                        ){
                                            Text(
                                                text = ingredient,
                                                fontSize = 12.sp,
                                                modifier = Modifier.weight(0.5f)
                                            )
                                            Text(
                                                text = measurement,
                                                fontSize = 12.sp,
                                                modifier = Modifier.weight(0.5f)
                                            )
                                            FloatingActionButton(
                                                onClick = {
                                                    val mutableList = ingredientList.toMutableList()
                                                    mutableList.remove(item)
                                                    ingredientList = mutableList.toList()

                                                },
                                                containerColor = Color.White,
                                                elevation = FloatingActionButtonDefaults.elevation(0.dp),
                                                modifier = Modifier.size(15.dp)) {
                                                Icon(
                                                    painter = painterResource(R.drawable.close),
                                                    contentDescription = "Remove Ingredient",
                                                    tint = Color.Red,
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }
                                        }
                                    } else {
                                        ingredient
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceEvenly,
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .padding(start =30.dp, end = 30.dp)
                                        ) {
                                            Text(
                                                text = ingredient,
                                                fontSize = 12.sp,
                                                modifier = Modifier.weight(0.5f)
                                            )
                                            FloatingActionButton(
                                                onClick = {
                                                    val mutableList = ingredientList.toMutableList()
                                                    mutableList.remove(item)
                                                    ingredientList = mutableList.toList()

                                                },
                                                containerColor = Color.White,
                                                elevation = FloatingActionButtonDefaults.elevation(0.dp),
                                                modifier = Modifier.size(15.dp)) {
                                                Icon(
                                                    painter = painterResource(R.drawable.close),
                                                    contentDescription = "Remove Ingredient",
                                                    tint = Color.Red,
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                    }
                }
            }
        }
    @Composable
    fun ProcedureInputList(onClose: () -> Unit) {
        var procedureText by remember { mutableStateOf("") }
        var procedureList by remember { mutableStateOf<List<String>>(emptyList()) }
        val context = LocalContext.current

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
                    .padding(bottom = 180.dp, start = 20.dp, end = 20.dp, top = 100.dp)
                    .fillMaxSize()
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
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(){
                    OutlinedTextField(
                        value = procedureText,
                        onValueChange = { procedureText = it },
                        label = { Text(text = "Procedure") },
                        singleLine = true,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 15.dp, end = 15.dp)


                    )
                        }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        FloatingActionButton(
                            onClick = {
                                val procedure = procedureText

                                if (procedure.isBlank()) {
                                    Toast.makeText(
                                        context,
                                        "Please enter a step",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    procedureList =
                                        procedureList + (procedure)
                                    procedureText = ""
                                }
                            },
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
                    Spacer(Modifier.height(20.dp))
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        items(procedureList) { item ->
                            val procedure = item
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(20.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(start = 30.dp, end = 30.dp)
                            ) {
                                Text(
                                    text = "Step ${procedureList.indexOf(item) + 1}",
                                    fontSize = 12.sp,
                                    modifier = Modifier.weight(0.5f)
                                )
                                Text(
                                    text = procedure,
                                    fontSize = 12.sp,
                                    modifier = Modifier.weight(0.5f)
                                )
                                FloatingActionButton(
                                    onClick = {

                                        val mutableList = procedureList.toMutableList()
                                        mutableList.remove(procedure)
                                        procedureList = mutableList.toList()

                                    },
                                    containerColor = Color.White,
                                    elevation = FloatingActionButtonDefaults.elevation(0.dp),
                                    modifier = Modifier.size(15.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.close),
                                        contentDescription = "Remove Ingredient",
                                        tint = Color.Red,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
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
        val icons = listOf(Icons.Filled.Home, Icons.Filled.Home, Icons.Filled.Home, Icons.Filled.Home)
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

    @Composable
    fun MyScreen() {
        var showIngredientInput by remember { mutableStateOf(false) }
        var showProcedureInput by remember { mutableStateOf(false) }

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
                        OverlayButton(
                            onAddClick = { showIngredientInput = true },
                            text = "Ingredients used"
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                    item {
                        OverlayButton(
                            onAddClick = { showProcedureInput = true },
                            text = "Procedure"
                        )
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
        if (showIngredientInput) {
            IngredientInputList(onClose = { showIngredientInput = false })
        }
        if (showProcedureInput) {
            ProcedureInputList(onClose = { showProcedureInput = false })
        }
        }

        @Preview(showBackground = true)
        @Composable
        fun GreetingPreview2() {
            com.example.hapag.ui.theme.HapagTheme {
                MyScreen()
            }
        }
}