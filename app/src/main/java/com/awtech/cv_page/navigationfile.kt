package com.awtech.cv_page

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController



@Composable
fun PageNav(viewmodel: ViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "CvScreen") {

        composable("CvScreen") { Personinfo(viewmodel) }
    }

}
