package com.example.hapag.composables.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hapag.dummyinputs.BlankRecipeScreen
import com.example.hapag.dummyinputs.HaloHaloRecipeScreen
import com.example.hapag.dummyinputs.LecheFlanRecipeScreen
import com.example.hapag.theme.AppTheme


@Composable
fun RecipeContent(recipeName: String?, onNavigateBack: () -> Unit) {
    when (recipeName) {
        "halohalo" -> HaloHaloRecipeScreen(onNavigateBack = onNavigateBack)
        "lecheflan" -> LecheFlanRecipeScreen(onNavigateBack = onNavigateBack)
        "blankrecipe" -> BlankRecipeScreen(onNavigateBack = onNavigateBack) // Added BlankRecipeScreen
        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colorScheme.background)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Recipe Not Found: $recipeName",
                    style = MaterialTheme.typography.headlineMedium,
                    color = AppTheme.colorScheme.background,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onNavigateBack, colors = ButtonDefaults.buttonColors(containerColor = AppTheme.colorScheme.background, contentColor = AppTheme.colorScheme.onBackground)) {
                    Text("Go Back")
                }
            }
        }
    }
}