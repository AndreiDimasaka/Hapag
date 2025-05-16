package com.example.hapag.composables.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.hapag.composables.UI.FigmaDashboardLayout
import com.example.hapag.theme.AppTheme

@Composable
fun HomeScreen(
    navController: NavController,
    paddingValues: PaddingValues
){
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
                navController = navController,
            )
        }
    }

