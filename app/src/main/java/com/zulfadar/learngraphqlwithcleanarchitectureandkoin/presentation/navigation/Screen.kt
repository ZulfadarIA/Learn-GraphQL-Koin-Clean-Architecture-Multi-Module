package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.navigation

sealed class Screen(val route: String) {
    data object LaunchList: Screen("main") //ganti naming objectnyapakai pascal case
    data object LaunchDetail: Screen("detail/{launchId}") {
        fun createRoute(launchId: String) = "detail/$launchId"
    }
    data object Login: Screen("login")
    data object CustomerService: Screen("customerService")
}