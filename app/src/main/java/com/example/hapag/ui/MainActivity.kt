package com.example.hapag

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.hapag.ui.BaseActivity
import com.example.hapag.ui.BottomNavigationBar
import com.example.hapag.ui.theme.buttonTextColor

class MainActivity : ComponentActivity() {
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
        val context = LocalContext.current
        Scaffold(
            bottomBar = {
                BottomNavigationBar(onItemSelected = { index ->
                    // Navigation for the bottom bar is handled within BottomNavigationBar
                    println("Main Activity: Bottom navigation item selected at index: $index")
                }, selectedIndex = 0)
            }
        ) { paddingValues ->
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(buttonTextColor)
                    .padding(paddingValues)
            ) {
                val dashboard = createRef()

                FigmaDashboardLayout(
                    modifier = Modifier.constrainAs(dashboard) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    buttonBackgroundColor = buttonBackgroundColor,
                    onRecipeClick = { recipeName ->
                        val formattedRecipeName = recipeName.lowercase().replace(" ", "").replace("-", "")
                        val intent = Intent(context, RecipeActivity::class.java).apply {
                            putExtra("recipe", formattedRecipeName)
                        }
                        context.startActivity(intent)
                    },
                    onUploadedClick = {
                        val intent = Intent(context, MyRecipesActivity::class.java)
                        context.startActivity(intent)
                    },
                    onFavoritesClick = {
                        val intent = Intent(context, MyFavoritesActivity::class.java)
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FigmaDashboardLayout(
    modifier: Modifier = Modifier,
    buttonBackgroundColor: Color,
    onRecipeClick: (String) -> Unit,
    onUploadedClick: () -> Unit,
    onFavoritesClick: () -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("All") }
    var selectedSweetSavoryTab by remember { mutableStateOf("All") }

    val sweetFilipinoFoods = remember {
        listOf(
            Triple("Halo-Halo", "Merienda", R.drawable.halohalo),
            Triple("Leche Flan", "Dessert", R.drawable.lecheflan),
            Triple("Ube Halaya", "Dessert", R.drawable.ubehalaya),
            Triple("Bibingka", "Breakfast", R.drawable.bibingka),
            Triple("Puto Bumbong", "Breakfast", R.drawable.bumbong),
            Triple("Kutsinta", "Merienda", R.drawable.kutsinta),
            Triple("Suman", "Breakfast", R.drawable.suman),
            Triple("Buko Pandan", "Dessert", R.drawable.bukopandan),
            Triple("Turon", "Merienda", R.drawable.turon),
            Triple("Banana Cue", "Merienda", R.drawable.bananacue)
        )
    }

    val savoryFilipinoFoods = remember {
        listOf(
            Triple("Pork Adobo", "Lunch", R.drawable.adobo),
            Triple("Pork Sinigang", "Dinner", R.drawable.sinigang),
            Triple("Tortang Talong", "Dinner", R.drawable.torta),
            Triple("Kare-Kare", "Lunch", R.drawable.karekare),
            Triple("Pinakbet", "Lunch", R.drawable.pinakbet),
            Triple("Laing", "Dinner", R.drawable.laing),
            Triple("Pancit Bihon", "Merienda", R.drawable.pancit),
            Triple("Lumpia", "Merienda", R.drawable.lumpia),
            Triple("Sisig", "Lunch", R.drawable.sisig),
            Triple("Bulalo", "Dinner", R.drawable.bulalo)
        )
    }

    val filteredFoods = remember(selectedCategory) {
        if (selectedCategory == "All") {
            sweetFilipinoFoods + savoryFilipinoFoods
        } else {
            (sweetFilipinoFoods + savoryFilipinoFoods).filter { it.second.equals(selectedCategory, ignoreCase = true) }
        }
    }

    val displayFoods = remember(selectedSweetSavoryTab, filteredFoods) {
        when (selectedSweetSavoryTab) {
            "All" -> filteredFoods
            "Sweet" -> filteredFoods.filter { it.first in sweetFilipinoFoods.map { it.first } }
            "Savory" -> filteredFoods.filter { it.first in savoryFilipinoFoods.map { it.first } }
            else -> filteredFoods
        }
    }

    Column(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
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
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilterButton(
                text = "All",
                isSelected = selectedCategory == "All",
                onClick = { selectedCategory = "All" },
                backgroundColor = buttonBackgroundColor,
                textColor = buttonTextColor
            )
            FilterButton(
                text = "Breakfast",
                isSelected = selectedCategory == "Breakfast",
                onClick = { selectedCategory = "Breakfast" },
                backgroundColor = buttonBackgroundColor,
                textColor = buttonTextColor
            )
            FilterButton(
                text = "Lunch",
                isSelected = selectedCategory == "Lunch",
                onClick = { selectedCategory = "Lunch" },
                backgroundColor = buttonBackgroundColor,
                textColor = buttonTextColor
            )
            FilterButton(
                text = "Merienda",
                isSelected = selectedCategory == "Merienda",
                onClick = { selectedCategory = "Merienda" },
                backgroundColor = buttonBackgroundColor,
                textColor = buttonTextColor
            )
            FilterButton(
                text = "Dinner",
                isSelected = selectedCategory == "Dinner",
                onClick = { selectedCategory = "Dinner" },
                backgroundColor = buttonBackgroundColor,
                textColor = buttonTextColor
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = onUploadedClick, // Call the onUploadedClick lambda
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonBackgroundColor,
                    contentColor = com.example.hapag.ui.theme.buttonTextColor
                ),
                shape = RoundedCornerShape(0.dp)
            ) { Text(text = "Uploaded") }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                modifier = Modifier.weight(1f),
                onClick = onFavoritesClick, // Call the onFavoritesClick lambda
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
                    targetValue = if (selectedSweetSavoryTab == "Sweet") Color.Black else Color.Gray,
                    animationSpec = tween(durationMillis = 300)
                ).value,
                modifier = Modifier
                    .weight(1f)
                    .clickable { selectedSweetSavoryTab = "Sweet" },
                textAlign = TextAlign.Center
            )
            Text(
                text = "Savory",
                color = animateColorAsState(
                    targetValue = if (selectedSweetSavoryTab == "Savory") Color.Black else Color.Gray,
                    animationSpec = tween(durationMillis = 300)
                ).value,
                modifier = Modifier
                    .weight(1f)
                    .clickable { selectedSweetSavoryTab = "Savory" },
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Divider(color = Color.LightGray, thickness = 3.dp)
        Spacer(modifier = Modifier.height(20.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            repeat(displayFoods.size / 2 + displayFoods.size % 2) { rowIndex ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    for (i in 0 until 2) {
                        val foodIndex = rowIndex * 2 + i
                        if (foodIndex < displayFoods.size) {
                            val (foodName, foodType, imageRes) = displayFoods[foodIndex]
                            RecipeCard(
                                foodName = foodName,
                                foodType = foodType,
                                imageRes = imageRes,
                                onClick = { onRecipeClick(foodName) }
                            )
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        Divider(color = Color.LightGray, thickness = 1.dp)
    }
}

@Composable
fun FilterButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    backgroundColor: Color,
    textColor: Color
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else backgroundColor,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else textColor
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = text)
    }
}

@Composable
fun RecipeCard(
    foodName: String = "Recipe Title",
    foodType: String = "Food Type",
    imageRes: Int? = null,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(170.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .size(170.dp, 100.dp)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            if (imageRes != null) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = foodName,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Text("No Image", textAlign = TextAlign.Center)
            }
        }
        Text(text = foodName, fontWeight = FontWeight.Bold)
        Text(text = foodType)
    }
}

@Composable
fun MainActivityScreenPreview() {
    MainActivity().MainActivityScreen()
}