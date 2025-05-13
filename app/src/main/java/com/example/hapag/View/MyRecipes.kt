package com.example.hapag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.hapag.DummyInputs.BlankRecipeScreen
import com.example.hapag.DummyInputs.HaloHaloRecipeScreen
import com.example.hapag.DummyInputs.LecheFlanRecipeScreen
import com.example.hapag.composables.RecipeContent
import com.example.hapag.composables.RecipeDetails
import com.example.hapag.theme.AppTheme
import com.example.hapag.ui.BottomNavigationBar

val buttonTextColor = Color(0xFFF1EDE7)
val buttonBackgroundColor = Color(0xFF403A35)

class RecipeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val recipeName = intent.getStringExtra("recipe")
                RecipeContent(recipeName = recipeName) { finish() }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHaloHaloRecipeScreen() {
    HaloHaloRecipeScreen(onNavigateBack = {})
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLecheFlanRecipeScreen() {
    LecheFlanRecipeScreen(onNavigateBack = {})
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBlankRecipeScreen() {
    BlankRecipeScreen(onNavigateBack = {})
}