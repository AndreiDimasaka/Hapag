
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.hapag.ViewModel.RecipeViewModel
import com.example.hapag.ViewModel.UploadViewModel
import com.example.hapag.composables.ImageSelect
import com.example.hapag.composables.ReorderableIngredientColumn
import com.example.hapag.composables.ReorderableProcedureColumn
import com.example.hapag.composables.ThemedTitleTextField
import com.example.hapag.composables.TopReturnBar
import com.example.hapag.theme.AppTheme


@Composable
fun UploadScreen(navController: NavController) {
    val uploadViewModel = viewModel<UploadViewModel>()
    val recipeViewModel = viewModel<RecipeViewModel>()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopReturnBar(
                title = "Upload",
                arrowBack = true,
                onNavigateBack = { navController.navigateUp() }
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
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
                ) {
                    item {
                        ThemedTitleTextField(
                            modifier = Modifier.fillMaxWidth(),
                            hint = "Title: Sinigang na baboy ",
                            onValueChange = { uploadViewModel.updateTitle(it) }
                        )
                        Spacer(Modifier.height(10.dp))
                        ThemedTitleTextField(
                            style = AppTheme.typography.bodySmall,
                            modifier = Modifier.fillMaxWidth(),
                            hint = "Share the inspiration for this recipe. Describe the dish's flavors, textures, and aroma, and tell us your favorite way to savor it.",
                            onValueChange = { uploadViewModel.updateDescription(it) }
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
                                modifier = Modifier.fillMaxWidth().padding(start = 120.dp),
                                hint = "3 People",
                                style = AppTheme.typography.bodySmall,
                                onValueChange = { uploadViewModel.updateServingSize(it) }
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
                                style = AppTheme.typography.bodySmall,
                                onValueChange = { uploadViewModel.updateCookTime(it) }
                            )
                        }
                        Spacer(Modifier.height(30.dp))
                        Text(
                            text = "Category",
                            color = AppTheme.colorScheme.onBackground,
                            style = AppTheme.typography.labelMedium,
                        )
                        Spacer(Modifier.height(10.dp))
                        uploadViewModel.categoryCheckBox.forEachIndexed { index, info ->
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
                                        uploadViewModel.categoryCheckBox[index] = uploadViewModel.categoryCheckBox[index].copy(isChecked = it)
                                    },
                                )
                                Text(
                                    text = info.text,
                                    style = AppTheme.typography.labelSmall,
                                    color = AppTheme.colorScheme.onBackground
                                )
                            }
                        }
                        Spacer(Modifier.height(30.dp))
                        OutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = 50.dp),
                            onClick = { uploadViewModel.openIngredientList() },
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
                            contentPadding = PaddingValues(horizontal = 50.dp),
                            onClick = { uploadViewModel.openProcedureList() },
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
                        Row(
                            Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = {
                                    uploadViewModel.uploadRecipe { recipe ->
                                        recipeViewModel.addMyRecipe(recipe)
                                        navController.navigate("recipe") {
                                            popUpTo("recipe") { inclusive = true }
                                        }
                                    }
                                },
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = AppTheme.colorScheme.secondary
                                )
                            ) {
                                Text(
                                    text = "UPLOAD",
                                    color = AppTheme.colorScheme.onSecondary,
                                    style = AppTheme.typography.labelLarge,
                                )
                            }
                        }
                    }
                }
            }
        }
        if (uploadViewModel.overlayIngredientList.value) {
            ReorderableIngredientColumn(onClose = { uploadViewModel.closeIngredientList() })
        }
        if (uploadViewModel.overlayProcedureList.value) {
            ReorderableProcedureColumn(onClose = { uploadViewModel.closeProcedureList() })
        }
    }
}