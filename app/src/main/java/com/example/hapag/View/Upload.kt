package com.example.hapag.View

import android.content.res.Configuration
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hapag.R
import com.example.hapag.ViewModel.UploadViewModel
import com.example.hapag.composables.ImageSelect
import com.example.hapag.composables.ReorderableIngredientColumn
import com.example.hapag.composables.ReorderableProcedureColumn
import com.example.hapag.composables.ThemedTitleTextField
import com.example.hapag.composables.TopReturnBar
import com.example.hapag.ui.BottomNavigationBar
import com.example.hapag.theme.AppTheme

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

@Composable
fun MyScreen() {
    val viewModel = viewModel<UploadViewModel>()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE


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
        topBar = { TopReturnBar(title = "Upload", arrowBack = false) },
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()) {
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
                )
                {
                    item {
                        ThemedTitleTextField(
                            modifier = Modifier.fillMaxWidth(),
                            hint = "Title: Sinigang na baboy ",
                            onValueChange = {onValueChange -> viewModel.title}
                        )
                        Spacer(Modifier.height(10.dp))
                        ThemedTitleTextField(
                            style = AppTheme.typography.bodySmall,
                            modifier = Modifier.fillMaxWidth(),
                            hint = "Share the inspiration for this recipe. Describe the dish's flavors, textures, and aroma, and tell us your favorite way to savor it.",
                            onValueChange = {onValueChange -> viewModel.description}
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
                                onValueChange = {onValueChange -> viewModel.servingSize}
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
                                onValueChange = {onValueChange -> viewModel.cookTime}
                            )
                        }
                        Spacer(Modifier.height(30.dp))
<<<<<<< HEAD:app/src/main/java/com/example/hapag/composables/screen/ScreenUpload.kt
                        Text(
                            text = "Category",
                            color = AppTheme.colorScheme.onBackground,
                            style = AppTheme.typography.labelMedium,
                        )
                        Spacer(Modifier.height(10.dp))
                        viewModel.categoryCheckBox.forEachIndexed{index, info ->
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
                                        viewModel.categoryCheckBox[index] = viewModel.categoryCheckBox[index].copy(isChecked = it)
                                    },
                                )
                                Text( text = info.text, style = AppTheme.typography.labelSmall, color = AppTheme.colorScheme.onBackground)
                            }
                        }

                        Spacer(Modifier.height(30.dp))
=======
>>>>>>> parent of 38f4f3f (starting to route viewmodels to recipescreen, myrecipe etc. Updated uplaod screen):app/src/main/java/com/example/hapag/View/Upload.kt
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
<<<<<<< HEAD:app/src/main/java/com/example/hapag/composables/screen/ScreenUpload.kt
<<<<<<< HEAD:app/src/main/java/com/example/hapag/composables/screen/ScreenUpload.kt
                                onClick = {
                                    uploadViewModel.uploadRecipe { recipe ->
                                        recipeViewModel.addMyRecipe(recipe)
                                        navController.navigate("recipe") {
                                            popUpTo("recipe") { inclusive = true }
                                        }
                                    }
                                },
=======
                                onClick = {TODO()},
>>>>>>> parent of 38f4f3f (starting to route viewmodels to recipescreen, myrecipe etc. Updated uplaod screen):app/src/main/java/com/example/hapag/View/Upload.kt
=======
                                onClick = {viewModel.uploadRecipe()},
>>>>>>> parent of e5dfdad (adding navcontroller):app/src/main/java/com/example/hapag/View/Upload.kt
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
}




@Preview(showBackground = true)
@Composable
fun UploadPreview() {
    AppTheme {
        MyScreen()
    }
}