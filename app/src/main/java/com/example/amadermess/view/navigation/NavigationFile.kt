package com.example.amadermess.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.amadermess.model.Screen
import com.example.amadermess.view.ui.AddMessMember
import com.example.amadermess.view.ui.ShowMessMembers
import com.example.amadermess.view.viewmodel.MainViewModel

@Composable
fun SetupNavigation(viewModel: MainViewModel? = null) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MemberScreen.route) {
        composable(route = Screen.MemberScreen.route) {
            ShowMessMembers(navController, viewModel)
        }
        composable(route = Screen.AddMember.route){
           AddMessMember(navController, viewModel)
        }

    }
}