package com.example.ai_assistant.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.Message
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ai_assistant.ui.theme.ColorSurf

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Chat : BottomNavItem(
        route = Screen.Chat.route,
        title = "Чат",
        icon = Icons.Rounded.Message
    )

    object Documents : BottomNavItem(
        route = Screen.Documents.route,
        title = "Документы",
        icon = Icons.Rounded.Description
    )

    object Actions : BottomNavItem(
        route = Screen.Actions.route,
        title = "Действия",
        icon = Icons.Rounded.Build
    )
}

@Composable
fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        BottomNavItem.Chat,
        BottomNavItem.Documents,
        BottomNavItem.Actions
    )

    NavigationBar(
        containerColor = Color(0xFFF5F5F5), // Светло-серый цвет
        tonalElevation = 8.dp,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            // Очищаем бэкстэк до корня при навигации
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            // Избегаем множественных копий того же экрана
                            launchSingleTop = true
                            // Восстанавливаем состояние при повторном нажатии
                            restoreState = true
                        }
                    }
                },
                label = {
                    Text(
                        text = item.title,
                        color = if (currentRoute == item.route) Color.Black else Color.Gray
                    )
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = if (currentRoute == item.route) Color.Black else Color.Gray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = ColorSurf,
                    selectedTextColor = Color.Black,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = ColorSurf
                )
            )
        }
    }
}