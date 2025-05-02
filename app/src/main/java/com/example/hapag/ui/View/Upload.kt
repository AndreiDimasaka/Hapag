package com.example.hapag.ui.View

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hapag.ui.ViewModel.IngredientILViewModel
import com.example.hapag.R
import com.example.hapag.composables.ImageSelect
import com.example.hapag.composables.ReorderableIngredientColumn
import com.example.hapag.composables.ReorderableProcedureColumn
import com.example.hapag.composables.ThemedTitleTextField
import com.example.hapag.composables.TopReturnBar
import com.example.hapag.ui.BottomNavigationBar
import com.example.hapag.ui.theme.AppTheme

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
    val viewModel = viewModel<IngredientILViewModel>()
    var openProcedureList by remember { mutableStateOf(false) }


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
                        .fillMaxHeight(0.25f)
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
                            hint = "Title: Sinigang na baboy "
                        )
                        Spacer(Modifier.height(10.dp))
                        ThemedTitleTextField(
                            style = AppTheme.typography.bodySmall,
                            modifier = Modifier.fillMaxWidth(),
                            hint = "Share the inspiration for this recipe. Describe the dish's flavors, textures, and aroma, and tell us your favorite way to savor it."
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
                                contentDescription = "Add Ingredients"
                            )
                            Text(
                                text = "ADD INGREDIENTS",
                                style = AppTheme.typography.labelMedium,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        OutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = (50.dp) ),
                            onClick = { viewModel.openIngredientList() },
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = AppTheme.colorScheme.onSecondary
                            ),
                            border = null
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_add),
                                contentDescription = "Add Ingredients"
                            )
                            Text(
                                text = "ADD STEPS",
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
                                onClick = {viewModel.openIngredientList()},
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = AppTheme.colorScheme.secondary
                                )
                            ) { Text(
                                text = "UPLOAD",
                                style = AppTheme.typography.labelLarge,
                            ) }
                        }
                    }
                }
            }
        }
        if (viewModel.overlayIngredientList) {
            ReorderableIngredientColumn(onClose = { viewModel.closeIngredientList()})
        }
        if(openProcedureList){
            ReorderableProcedureColumn (onClose = {openProcedureList = false } )
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