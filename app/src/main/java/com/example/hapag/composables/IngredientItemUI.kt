package com.example.hapag.composables

import android.graphics.Insets.add
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapag.R
import com.example.hapag.ui.theme.AppTheme
import sh.calvin.reorderable.rememberReorderableLazyListState
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.ReorderableLazyListState


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
data class Item(val id: Int, val text: String)

@Composable
fun ReorderableColumn() {
    var items by remember { mutableStateOf(
        List(10) { Item(it, "Item #$it") }
    )}
    val lazyListState = rememberLazyListState()

    val reorderableState = rememberReorderableLazyListState(lazyListState) { from, to ->
        items = items.toMutableList().apply {
                val fromIndex = from.index
                val toIndex = if (to.index > fromIndex) to.index - 1 else to.index
                add(toIndex, removeAt(fromIndex))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(vertical = 8.dp),
        ) {
            itemsIndexed(items, key = { _, item -> item.id }) { index, item ->
                ReorderableItem(reorderableState, key = item.id) { isDragging ->
                    Box(
                        modifier = Modifier
                            .draggableHandle()
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(if (isDragging) Color.Gray.copy(0.3f) else Color.LightGray)
                            .padding(16.dp)
                    ) {
                        Text(text = item.text, style = AppTheme.typography.bodyLarge)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Add a new item
            items = items + Item(items.size, "Item #${items.size}")
        }) {
            Text("Add Item")
        }
    }
}


@Preview(showBackground = true)
@Composable fun IngredientInputListPreview()
{
    ReorderableColumn()
}
