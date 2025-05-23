package com.example.hapag.composables.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hapag.R
import com.example.hapag.theme.AppTheme
import com.example.hapag.viewModel.UploadViewModel
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.rememberReorderableLazyListState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReorderableIngredientColumn(
    onClose: () -> Unit
) {
    var viewModel = viewModel<UploadViewModel>()
    val lazyListState = rememberLazyListState()

    val reorderableLazyListState = rememberReorderableLazyListState(lazyListState) { from, to ->
        viewModel.reorderIngredients(fromIndex = from.index, toIndex = to.index)
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
                    viewModel.ingredientList,
                    key = { _, item -> item.id}) { index, ingredientItem ->
                    ReorderableItem(
                        reorderableLazyListState,
                        key = ingredientItem.id
                    ) { isDragging ->

                        TextItemRow(
                            item = ingredientItem.text,
                            onTextChange = { newText ->
                                viewModel.updateIngredient(ingredientItem.id,newText)
                            },

                            onOptionsClick = {
                                viewModel.removeIngredient(ingredientItem.id)
                            },
                            hint = "1 clove of garlic",
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
                            viewModel.addIngredient()
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
        ReorderableIngredientColumn(onClose = {})
    }
}
