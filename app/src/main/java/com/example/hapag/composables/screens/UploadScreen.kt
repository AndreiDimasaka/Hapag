package com.example.hapag.composables.screens

import android.content.res.Configuration
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hapag.R
import com.example.hapag.composables.ReorderableProcedureColumn
import com.example.hapag.composables.widgets.ImageSelect
import com.example.hapag.composables.widgets.ReorderableIngredientColumn
import com.example.hapag.composables.widgets.ThemedTitleTextField
import com.example.hapag.model.RecipeEvent
import com.example.hapag.model.RecipeState
import com.example.hapag.theme.AppTheme
import com.example.hapag.viewModel.SharedViewModel
import com.example.hapag.viewModel.UploadViewModel

@Composable
fun UploadScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    sharedViewModel: SharedViewModel = viewModel(),
    state: RecipeState = RecipeState(),
    onEvent: (RecipeEvent) -> Unit
    ) {
    val viewModel = viewModel<UploadViewModel>()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val category1 = viewModel.categories1.collectAsState()
    val category2 = viewModel.categories2.collectAsState()
    val isValid = remember { mutableStateOf(true) }



    Box(modifier = Modifier
            .fillMaxSize()) {
            ConstraintLayout(
                modifier = Modifier
                    .padding(paddingValues)
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
                        .fillMaxHeight(if (isLandscape) 0.5f else 0.25f)
                        .background(AppTheme.colorScheme.tertiary.copy(alpha = 0.7f))
                ) {
                    ImageSelect()
                }
                LazyColumn(
                    modifier = Modifier
                        .constrainAs(lazyColumnRef) {
                            top.linkTo(imageBoxRef.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)

                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        },
                    contentPadding = PaddingValues(15.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                )
                {
                    item {
                        ThemedTitleTextField(
                            initialValue = "",
                            modifier = Modifier.fillMaxWidth(),
                            hint = "Title: Sinigang na baboy ",
                            onValueChange = {onEvent(RecipeEvent.SetTitle(it))}
                        )
                        Spacer(Modifier.height(10.dp))
                        ThemedTitleTextField(
                            initialValue = "",
                            style = AppTheme.typography.bodySmall,
                            modifier = Modifier.fillMaxWidth(),
                            hint = "Share the inspiration for this recipe. Describe the dish's flavors, textures, and aroma, and tell us your favorite way to savor it.",
                            onValueChange = {onEvent(RecipeEvent.SetDescription(it))}
                        )
                        Spacer(Modifier.height(20.dp))
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
                                initialValue = "",
                                modifier = Modifier.fillMaxWidth().padding(start = 120.dp),
                                hint = "3 People",
                                style = AppTheme.typography.bodySmall,
                                onValueChange = {
                                    onEvent(RecipeEvent.SetServingSize(it.toIntOrNull() ?: 0))
                                    isValid.value = it.toIntOrNull() != null
                                },
                                isValid = isValid.value
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
                                initialValue = "",
                                modifier = Modifier.fillMaxWidth().padding(start = 83.dp),
                                hint = "1 hr 10 mins",
                                style = AppTheme.typography.bodySmall,
                                onValueChange = {onEvent(RecipeEvent.SetCookTime(it))}
                            )
                        }
                        Spacer(Modifier.height(30.dp))
                        Text(
                            text = "Category",
                            color = AppTheme.colorScheme.onBackground,
                            style = AppTheme.typography.labelMedium,
                        )
                        Spacer(Modifier.height(10.dp))
                        category1.value.forEachIndexed{index, info ->
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Checkbox(
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = AppTheme.colorScheme.secondary,
                                        uncheckedColor = AppTheme.colorScheme.secondary,
                                        checkmarkColor = AppTheme.colorScheme.background
                                    ),
                                    checked = info.isChecked,
                                    onCheckedChange = {
                                        viewModel.toggleCategory1(index)
                                    },
                                )
                                Text( text = info.text, style = AppTheme.typography.labelSmall, color = AppTheme.colorScheme.onBackground)
                            }
                        }
                        Spacer(Modifier.height(10.dp))

                        Text(
                            text = "Taste",
                            color = AppTheme.colorScheme.onBackground,
                            style = AppTheme.typography.labelMedium,
                        )

                        category2.value.forEachIndexed{index, info ->
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Checkbox(
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = AppTheme.colorScheme.secondary,
                                        uncheckedColor = AppTheme.colorScheme.secondary,
                                        checkmarkColor = AppTheme.colorScheme.background
                                    ),
                                    checked = info.isChecked,
                                    onCheckedChange = {
                                        viewModel.toggleCategory2(index)
                                    },
                                )
                                Text( text = info.text, style = AppTheme.typography.labelSmall, color = AppTheme.colorScheme.onBackground)
                            }
                        }

                        Spacer(Modifier.height(30.dp))
                        OutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = (50.dp) ),
                            onClick = {viewModel.openIngredientList()},
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = AppTheme.colorScheme.onSecondary
                            ),
                            border = null
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_add),
                                contentDescription = "Add Ingredients",
                                tint = AppTheme.colorScheme.onBackground
                            )
                            Text(
                                text = "ADD INGREDIENTS",
                                color = AppTheme.colorScheme.onBackground,
                                style = AppTheme.typography.labelMedium,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        OutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = (50.dp) ),
                            onClick = {viewModel.openProcedureList()},
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = AppTheme.colorScheme.onSecondary
                            ),
                            border = null
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_add),
                                contentDescription = "Add Steps",
                                tint = AppTheme.colorScheme.onBackground
                            )
                            Text(
                                text = "ADD STEPS",
                                color = AppTheme.colorScheme.onBackground,
                                style = AppTheme.typography.labelMedium,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Row (
                            Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center
                        ){
                            Button(
                                onClick = {
                                    val uploadedRecipe = viewModel.uploadRecipe()

                                    if (uploadedRecipe != null) {
                                        sharedViewModel.addToRecipeList(uploadedRecipe)
                                        sharedViewModel.addToMyRecipe(uploadedRecipe)

                                        navController.navigate("MyRecipes/${uploadedRecipe.title}")
                                    } else {
                                        // Optionally show a snackbar or toast:
                                        // "Please complete all required fields."
                                    }},
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = AppTheme.colorScheme.secondary
                                )
                            ) { Text(
                                text = "UPLOAD",
                                color = AppTheme.colorScheme.onSecondary,
                                style = AppTheme.typography.labelLarge,
                            ) }
                        }
                    }
                }
            }
        }
        if (viewModel.overlayIngredientList) {
            ReorderableIngredientColumn(onClose = {viewModel.closeIngredientList()})
        }
        if(viewModel.overlayProcedureList){
            ReorderableProcedureColumn (onClose = {viewModel.closeProcedureList()} )
        }
}



