package com.example.hapag.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.example.hapag.composables.widgets.TextItemRow
import com.example.hapag.viewModel.UploadViewModel
import com.example.hapag.theme.AppTheme
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.rememberReorderableLazyListState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReorderableProcedureColumn(
    onClose: () -> Unit
) {
    var viewModel = viewModel<UploadViewModel>()

    val lazyListState = rememberLazyListState()
    val reorderableLazyListState = rememberReorderableLazyListState(lazyListState) { from, to ->
        viewModel.reorderProcedure(fromIndex = from.index, toIndex = to.index)
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
                contentPadding = PaddingValues(5.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(
                    viewModel.procedureList,
                    key = { _, item -> item.id }) { index, procedureItem ->
                    ReorderableItem(
                        reorderableLazyListState,
                        key = procedureItem.id
                    ) { isDragging ->
                        Column(
                            Modifier.fillMaxSize().padding(start = 16.dp)
                        ) {
                            Text(
                                text = "Step ${index + 1}",
                                style = AppTheme.typography.labelMedium,
                                color = AppTheme.colorScheme.onBackground
                            )
                            TextItemRow(
                                item = procedureItem.text,
                                onTextChange = { newText ->
                                    viewModel.updateProcedure(procedureItem.id, newText)
                                    },
                                onOptionsClick = {
                                    viewModel.removeProcedure(procedureItem.id)
                                },
                                hint = "Heat oil in pan and sauté garlic and onions add chicken to the pan and sear on all sides",
                                reorderHandlerModifier = Modifier
                                    .draggableHandle()
                                    .background(Color.Transparent)
                                    .size(24.dp)
                            )
                        }
                    }
                }
                item {
                    OutlinedButton(
                        onClick = {
                            viewModel.addProcedure()
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
                            text = "+ Step",
                            style = AppTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable fun ProcedureInputListPreview()
{
    AppTheme {
        ReorderableProcedureColumn {  }
    }
}
