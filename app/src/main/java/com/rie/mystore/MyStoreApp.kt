package com.rie.mystore

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.rie.mystore.ui.screen.*
import com.rie.mystore.ui.theme.MyStoreTheme

@Composable
@Preview(showBackground = true)
fun MyStoreAppPreview() {
    MyStoreTheme {
        MyStoreApp()
    }
}

@Composable
fun MyStoreApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != MrScreen.Detailvivo.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MrScreen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(MrScreen.Home.route) {
                MrHome(
                    navigateToDetail = { vivoId ->
                        navController.navigate(MrScreen.Detailvivo.createRoute(vivoId))
                    }
                )
            }
            composable(MrScreen.Profile.route) {
                MrProfile()
            }
            composable(
                route = MrScreen.Detailvivo.route,
                arguments = listOf(navArgument("vivoId") { type = NavType.LongType })
            ) { navBackStackEntry ->
                val id = navBackStackEntry.arguments?.getLong("vivoId") ?: -1L
                MrDetail(
                    vivoId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val navigationItems = listOf(
        MrNavigation(
            title = stringResource(R.string.menu_home),
            icon = Icons.Default.Home,
            screen = MrScreen.Home,
            contentDescription = stringResource(R.string.menu_home)
        ),
        MrNavigation(
            title = stringResource(R.string.about_page),
            icon = Icons.Default.AccountCircle,
            screen = MrScreen.Profile,
            contentDescription = stringResource(R.string.about_page)
        )
    )

    BottomNavigation(modifier = modifier) {
        navigationItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.contentDescription
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
