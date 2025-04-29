package com.example.hapag.ui.Front

import android.content.ClipData.Item
import android.graphics.Insets.add
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.hapag.composables.ImageSelect
import com.example.hapag.composables.IngredientItemRow
import com.example.hapag.composables.ThemedTitleTextField
import com.example.hapag.composables.TopReturnBar
import com.example.hapag.ui.BottomNavigationBar
import com.example.hapag.ui.theme.AppTheme
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.rememberReorderableLazyListState

class Upload : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                MyScreen()
                }
            }
        }
    }

data class Item(val id: Int, val text: String)


@Composable
fun MyScreen() {
    var nextId by rememberSaveable { mutableStateOf(2) }
    var ingredientList by remember { mutableStateOf(
        listOf(
        Item(id = 1, text = "1 tbsp of soy sauce"), Item(id = 2, text = "1 tbsp of soy sauce")

        ))}

    val lazyListState = rememberLazyListState()
    val reorderableLazyListState = rememberReorderableLazyListState(lazyListState) { from, to ->
        ingredientList = ingredientList.toMutableList().apply {
            val fromIndex = from.index
            val toIndex = if (to.index > fromIndex) to.index + 1 else to.index
            add(toIndex, removeAt(fromIndex))
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                onItemSelected = { index ->
                    println("Bottom navigation item selected in Upload: $index")
                },
                selectedIndex = 1
            )
        },
        topBar = { TopReturnBar(title = "Upload") },
    ) { innerPadding ->
        ConstraintLayout(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(AppTheme.colorScheme.background)
        ) {
            val (imageBoxRef, lazyColumnRef) = createRefs()

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .constrainAs(imageBoxRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .fillMaxHeight(0.30f)
                    .background(AppTheme.colorScheme.tertiary.copy(alpha = 0.7f))
            ) {
                ImageSelect()
            }
            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .constrainAs(lazyColumnRef) {
                        top.linkTo(imageBoxRef.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)

                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    },
                verticalArrangement = Arrangement.spacedBy(15.dp),
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 8.dp),
                )
            {
                item {
                    ThemedTitleTextField(
                        modifier = Modifier.fillMaxWidth(),
                        hint = "Title: Sinigang na baboy "
                    )

                    Spacer(Modifier.height(15.dp))

                    ThemedTitleTextField(
                        style = AppTheme.typography.bodySmall,
                        modifier = Modifier.fillMaxWidth(),
                        hint = "Share the inspiration for this recipe. Describe the dish's flavors, textures, and aroma, and tell us your favorite way to savor it."
                    )
                    Spacer(Modifier.height(30.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Serves",
                            color = AppTheme.colorScheme.onBackground,
                            style = AppTheme.typography.labelMedium,
                        )
                        Spacer(Modifier.weight(1f))

                        ThemedTitleTextField(
                            modifier = Modifier.fillMaxWidth().padding(start = 120.dp),
                            hint = "3 People",
                            style = AppTheme.typography.bodySmall
                        )
                    }
                    Spacer(Modifier.height(20.dp))

                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Cook Time",
                            color = AppTheme.colorScheme.onBackground,
                            style = AppTheme.typography.labelMedium,
                        )
                        Spacer(Modifier.weight(1f))

                        ThemedTitleTextField(
                            modifier = Modifier.fillMaxWidth().padding(start = 83.dp),
                            hint = "1 hr 10 mins",
                            style = AppTheme.typography.bodySmall
                        )
                    }
                    Spacer(Modifier.height(40.dp))
                    Text(
                        text = "Ingredients",
                        color = AppTheme.colorScheme.onBackground,
                        style = AppTheme.typography.labelLarge
                    )
                }
                itemsIndexed(

                    ingredientList,
                    key = { _, item -> item.id }) { index, ingredientItem ->
                    ReorderableItem(reorderableLazyListState, key = ingredientItem.id) { isDragging ->

                        IngredientItemRow(
                            item = ingredientItem.text,
                            onTextChange = { newText ->
                                ingredientList = ingredientList.map {
                                    if (it.id == ingredientItem.id) {
                                        it.copy(text = newText)
                                    } else {
                                        it
                                    }
                                }
                            },
                            onOptionsClick = {
                                ingredientList =
                                    ingredientList.filter { it.id != ingredientItem.id }
                            },
                            reorderHandlerModifier = Modifier
                                .draggableHandle()
                                .background(Color.Transparent)
                                .padding(8.dp)
                                .size(24.dp)
                        )
                    }
                }
                item{
                   OutlinedButton(
                        onClick = {
                            val newItem = Item(
                                id = nextId,
                                text = "1 tbsp of soy sauce",
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = AppTheme.colorScheme.onSecondary,
                            containerColor = Color.Transparent
                        ),
                        border = null
                    ) {
                        Text(
                            text = "+ Ingredient",
                            style = AppTheme.typography.labelMedium
                        )
                    }
                }

                }
            }
        }
    }




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        MyScreen()
    }
}