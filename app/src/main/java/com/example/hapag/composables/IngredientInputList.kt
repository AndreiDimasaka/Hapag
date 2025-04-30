package com.example.hapag.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapag.R
import com.example.hapag.ui.Front.Item
import com.example.hapag.ui.theme.AppTheme
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.rememberReorderableLazyListState


data class Item(val id: Int, val text: String)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IngredientItemRow(
    item: String,
    onTextChange: (String) -> Unit,
    onOptionsClick: () -> Unit,
    modifier: Modifier = Modifier,
    reorderHandlerModifier: Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = AppTheme.colorScheme.background,
                shape = AppTheme.shape.container)
            .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
            )
    {
        Icon(
            painter = painterResource( R.drawable.baseline_dehaze),
            contentDescription = null,
            tint = AppTheme.colorScheme.secondary,
            modifier = reorderHandlerModifier
        )
        TextField(
            shape = AppTheme.shape.textbox,
            value = item,
            onValueChange = onTextChange,
            modifier = Modifier
                .weight(1f)
                .background(Color.Transparent),
            colors = TextFieldDefaults.colors(



                focusedContainerColor = AppTheme.colorScheme.tertiary,
                unfocusedContainerColor = AppTheme.colorScheme.tertiary,
                disabledContainerColor = AppTheme.colorScheme.tertiary,
                errorContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent, // Remove underline indicator
                unfocusedIndicatorColor = Color.Transparent, // Remove underline indicator
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            ),
            textStyle = AppTheme.typography.bodyMedium,
            placeholder = {
                Text (
                text = "1 clove of garlic",
                style = AppTheme.typography.bodyMedium,
                color = AppTheme.colorScheme.onBackground) }
        )
        IconButton(onClick = onOptionsClick) {
            Icon(
                painter = painterResource(R.drawable.close),
                contentDescription = "Options",
                tint = AppTheme.colorScheme.secondary
            )
        }

    }
}

@Composable
fun ReorderableColumn(
    onClose: () -> Unit
) {
    var nextId by rememberSaveable { mutableStateOf(3) }
    var ingredientList by remember {
        mutableStateOf(
            listOf(
                Item(id = 1, text = ""),
                Item(id = 2, text = "")
            )
        )
    }

    val lazyListState = rememberLazyListState()
    val reorderableLazyListState = rememberReorderableLazyListState(lazyListState) { from, to ->
        ingredientList = ingredientList.toMutableList().apply {
            val fromIndex = from.index

            add(to.index, removeAt(fromIndex))
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = AppTheme.colorScheme.background
            ),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(bottom = 130.dp, start = 20.dp, end = 20.dp, top = 100.dp)
                .fillMaxSize()
        ) {
            IconButton(onClick = onClose) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = "return",
                    tint = AppTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(start = 10.dp)
                        .size(30.dp)
                )
            }

            LazyColumn(
                state = lazyListState,
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(
                    ingredientList,
                    key = { _, item -> item.id }) { index, ingredientItem ->
                    ReorderableItem(
                        reorderableLazyListState,
                        key = ingredientItem.id
                    ) { isDragging ->

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
                item {
                    OutlinedButton(
                        onClick = {
                            ingredientList = ingredientList + Item(nextId++, "")
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
@Composable fun IngredientInputListPreview()
{
    AppTheme {
        ReorderableColumn(onClose = {})
    }
}
