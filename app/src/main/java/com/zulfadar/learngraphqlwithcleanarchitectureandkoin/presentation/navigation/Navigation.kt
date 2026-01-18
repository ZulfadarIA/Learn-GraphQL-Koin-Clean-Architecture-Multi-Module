package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zulfadar.feature.launchdetail.LaunchDetailScreen
import com.zulfadar.feature.launchlist.LaunchListScreen
import com.zulfadar.feature.login.LoginScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.launchList.route) {
        composable(Screen.launchList.route) {
            LaunchListScreen(
                onLaunchClick = { launchId ->
                    navController.navigate(Screen.launchDetail.createRoute(launchId))
                }
            )
        }
        composable(
            route = Screen.launchDetail.route,
            arguments = listOf(navArgument("launchId") { type = NavType.StringType })
        ) { backstackEntry ->
            val launchId = backstackEntry.arguments!!.getString("launchId")!!
            LaunchDetailScreen(
                launchId = launchId,
                navigateToLogin = {
                    navController.navigate(Screen.login.route)
                }
            )
        }

        composable(Screen.login.route) {
            LoginScreen(
                navigateBack = {
                    navController.popBackStack()
                },
            )
        }
    }
}