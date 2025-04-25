package com.awtech.cv_page

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController



@Composable
fun PageNav(viewmodel: ViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Signin") {

        composable("CvScreen") { Personinfo(viewmodel) }
        composable("CreateAccount") { CreateAccount(viewmodel) }
        composable("Signin") {
            backStackEntry -> SigninScreen(viewModel = viewmodel,
            createaccount = { navController.navigate("CreateAccount") },
            forgotpassword = {navController.navigate("Retrievepassword")},
            signin = {destination -> navController.navigate(destination)}
            )}

        composable("Editprofile") { EditProfile() }
        composable("MyAccount") { MyAccount() }
        composable("Retrievepassword") { Retrieve() }
    }

}
