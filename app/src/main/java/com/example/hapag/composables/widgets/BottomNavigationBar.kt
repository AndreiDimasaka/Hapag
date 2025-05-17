package com.example.hapag.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.hapag.data.Screen

import com.example.hapag.theme.AppTheme

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        tonalElevation = 0.dp,
        containerColor = AppTheme.colorScheme.secondary,
    ) {
        Screen.bottomNavScreens.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)

                        ) {
                            Icon(
                                painter = painterResource(id = screen.icon),
                                contentDescription = screen.route,
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                },
                colors = NavigationBarItemColors(
                    selectedIndicatorColor = AppTheme.colorScheme.onPrimary,
                    selectedIconColor = AppTheme.colorScheme.primary,
                    selectedTextColor = AppTheme.colorScheme.primary,
                    unselectedIconColor = AppTheme.colorScheme.onSecondary,
                    unselectedTextColor = AppTheme.colorScheme.onSecondary,
                    disabledIconColor = TODO(),
                    disabledTextColor = TODO()
                ),
                label = { Text (
                    screen.route,
                    fontSize = 10.sp,
                    style = AppTheme.typography.labelSmall
                )
                        },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }

            )
        }
    }
}