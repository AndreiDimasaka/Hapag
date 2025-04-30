package com.example.hapag

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.hapag.ui.BottomNavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

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
    onRecipeClick: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("All") }
    var selectedSweetSavoryTab by remember { mutableStateOf("All") }
    val categories = listOf("All", "Breakfast", "Lunch", "Merienda", "Dinner")

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

    val filteredByCategory = remember(selectedCategory) {
        if (selectedCategory == "All") {
            sweetFilipinoFoods + savoryFilipinoFoods
        } else {
            (sweetFilipinoFoods + savoryFilipinoFoods).filter { it.second.equals(selectedCategory, ignoreCase = true) }
        }
    }

    val displayFoods = remember(selectedSweetSavoryTab, filteredByCategory) {
        when (selectedSweetSavoryTab) {
            "All" -> filteredByCategory
            "Sweet" -> filteredByCategory.filter { it.first in sweetFilipinoFoods.map { it.first } }
            "Savory" -> filteredByCategory.filter { it.first in savoryFilipinoFoods.map { it.first } }
            else -> filteredByCategory
        }
    }

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE
    val columnCount = if (isLandscape) 4 else 2

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            SearchBar(
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "search", ) },
                modifier = Modifier.weight(1f),
                query = searchText,
                onQueryChange = { searchText = it },
                onSearch = { isActive = false },
                active = isActive,
                onActiveChange = { isActive = it },
                placeholder = { Text("Search") }
            ) {}
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Category",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(categories, key= {it}) { category ->

                FilterButton(
                    text = category,
                    isSelected = selectedCategory == category,
                    onClick = { selectedCategory = category },
                    backgroundColor = buttonBackgroundColor,
                    textColor = buttonTextColor,
                    modifier = Modifier.widthIn(min = if (isLandscape) 220.dp else Dp.Unspecified).padding(start = 6.dp, end = 6.dp)
                )

            }

        }

        Divider(color = Color.LightGray, thickness = 3.dp)
        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Text(
                text = "All",
                color = animateColorAsState(
                    targetValue = if (selectedSweetSavoryTab == "All") Color.Black else Color.Gray,
                    animationSpec = tween(durationMillis = 300)
                ).value,
                modifier = Modifier
                    .weight(1f)
                    .clickable { selectedSweetSavoryTab = "All" },
                textAlign = TextAlign.Center
            )
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

        Column(horizontalAlignment = Alignment.Start) {
            for (rowIndex in 0 until (displayFoods.size + columnCount - 1) / columnCount) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    for (i in 0 until columnCount) {
                        val foodIndex = rowIndex * columnCount + i
                        if (foodIndex < displayFoods.size) {
                            val (foodName, foodType, imageRes) = displayFoods[foodIndex]
                            RecipeCard(
                                foodName = foodName,
                                foodType = foodType,
                                imageRes = imageRes,
                                onClick = { onRecipeClick(foodName) },
                                modifier = Modifier.weight(1f)
                            )
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                        if (i < columnCount - 1 && foodIndex < displayFoods.size - 1) {
                            Spacer(modifier = Modifier.width(8.dp))
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
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Button(
        modifier =
        modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else backgroundColor,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else textColor
        ),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(text = text, textAlign = TextAlign.Center)
    }
}

@Composable
fun RecipeCard(
    foodName: String = "Recipe Title",
    foodType: String = "Food Type",
    imageRes: Int? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(4.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.7f)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            if (imageRes != null) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = foodName,
                    modifier = Modifier.fillMaxSize()
                        .aspectRatio(1.7f),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )
            } else {
                Text("No Image", textAlign = TextAlign.Center)
            }
        }
        Text(text = foodName, fontWeight = FontWeight.Bold)
        Text(text = foodType)
    }
}

@Preview
@Composable
fun MainActivityScreenPreview() {
    MainActivity().MainActivityScreen()
}