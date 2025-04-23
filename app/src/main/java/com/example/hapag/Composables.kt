package com.example.hapag

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DarkBrown
import com.example.ui.theme.Light

class Composables {
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
                colors = CardDefaults.cardColors(Light),
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
                            color = DarkBrown,
                            modifier = Modifier.padding(end = 70.dp)
                        )
                        Text(
                            text = "Optional",
                            color = DarkBrown,
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
                            containerColor = Light,
                            elevation = FloatingActionButtonDefaults.elevation(0.dp),
                        ) { Icon(
                            painter = painterResource(R.drawable.add_circle),
                            contentDescription = "Add Ingredient",
                            tint = DarkBrown,
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
                                        color = DarkBrown,
                                        modifier = Modifier.weight(0.5f)
                                    )
                                    Text(
                                        text = measurement,
                                        fontSize = 12.sp,
                                        color = DarkBrown,
                                        modifier = Modifier.weight(0.5f)
                                    )
                                    FloatingActionButton(
                                        onClick = {
                                            val mutableList = ingredientList.toMutableList()
                                            mutableList.remove(item)
                                            ingredientList = mutableList.toList()

                                        },
                                        containerColor = Light,
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
                                        color = DarkBrown,
                                        modifier = Modifier.weight(0.5f)
                                    )
                                    FloatingActionButton(
                                        onClick = {
                                            val mutableList = ingredientList.toMutableList()
                                            mutableList.remove(item)
                                            ingredientList = mutableList.toList()

                                        },
                                        containerColor = Light,
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
                colors = CardDefaults.cardColors(Light),
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
                            containerColor = Light,
                            elevation = FloatingActionButtonDefaults.elevation(0.dp),
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.add_circle),
                                 contentDescription = "Add Step",
                                tint = DarkBrown,
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
                                    color = DarkBrown,
                                    modifier = Modifier.weight(0.5f)
                                )
                                Text(
                                    text = procedure,
                                    fontSize = 12.sp,
                                    color = DarkBrown,
                                    modifier = Modifier.weight(0.5f)
                                )
                                FloatingActionButton(
                                    onClick = {

                                        val mutableList = procedureList.toMutableList()
                                        mutableList.remove(procedure)
                                        procedureList = mutableList.toList()

                                    },
                                    containerColor = Light,
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
}