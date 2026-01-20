package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zulfadar.feature.launchdetail.LaunchDetailRoot
import com.zulfadar.feature.launchlist.LaunchListRoot
import com.zulfadar.feature.login.LoginRoot

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.LaunchList.route
    ) {
        composable(Screen.LaunchList.route) {
            LaunchListRoot(
                onLaunchClick = { launchId ->
                    navController.navigate(Screen.LaunchDetail.createRoute(launchId))
                },
                onCsFabClick = {}
            )
        }
        composable(
            route = Screen.LaunchDetail.route,
            arguments = listOf(navArgument("launchId") { type = NavType.StringType })
        ) { backstackEntry ->
            val launchId = backstackEntry.arguments?.getString("launchId") ?: "" //diganti nullable
            LaunchDetailRoot(
                launchId = launchId,
                navigateToLogin = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }

        composable(Screen.Login.route) {
            LoginRoot(
                navigateBack = {
                    navController.popBackStack()
                },
            )
        }

        composable(Screen.CustomerService.route) {
        }
    }
}