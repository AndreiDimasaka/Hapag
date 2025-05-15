package com.example.hapag

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.hapag.composables.FigmaDashboardLayout
import com.example.hapag.composables.TopReturnBar
import com.example.hapag.theme.AppTheme
import com.example.hapag.ui.BottomNavigationBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                MainActivityScreen()
            }
        }
    }

    @Composable
    @Preview(showBackground = true, showSystemUi = true)
    fun MainActivityScreen() {
        val context = LocalContext.current
        Scaffold(
            bottomBar = {
                BottomNavigationBar(onItemSelected = { index ->
                    println("Main Activity: Bottom navigation item selected at index: $index")
                }, selectedIndex = 0)
            },
            topBar = {
                TopReturnBar(title = "Home", onNavigateBack = {}, arrowBack = false)
            }
        ) { paddingValues ->
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colorScheme.background)
                    .padding(paddingValues)
            ) {
                val dashboard = createRef()
                FigmaDashboardLayout(
                    modifier = Modifier.constrainAs(dashboard) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    buttonBackgroundColor = buttonBackgroundColor,
                    onRecipeClick = { recipeName ->
                        val formattedRecipeName = recipeName.lowercase().replace(" ", "").replace("-", "")
                        val intent = Intent(context, RecipeActivity::class.java).apply {
                            putExtra("recipe", formattedRecipeName)
                        }
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}


@Preview
@Composable
fun MainActivityScreenPreview() {
    AppTheme {
        MainActivity().MainActivityScreen()
    }
}