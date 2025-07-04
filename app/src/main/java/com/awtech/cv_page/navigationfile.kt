package com.awtech.cv_page

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute


@Composable
fun PageNav(viewmodel: ViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Signin") {

        composable("CvScreen") { Personinfo(viewmodel) }
        composable("CreateAccount") { backStackEntry -> CreateAccount(viewmodel, created = {
            navController.navigate(route = "Editprofile")

        }) }

        composable("Signin") {
            backStackEntry -> SigninScreen(viewModel = viewmodel,
            createaccount = { navController.navigate("CreateAccount") },
            forgotpassword = {navController.navigate("Retrievepassword")},
            signin = { destination -> navController.navigate(destination)}
            )}

        composable("Editprofile") { EditProfile(viewModel = viewmodel, Tostartscreen = {
            destination -> navController.navigate(destination)}) }
        composable("MyAccount") { MyAccount() }

        //composable("StartPage") { StartPage(viewmodel) }
        composable<Person> { backStackEntry -> val person: Person = backStackEntry.toRoute()
            StartPage(viewmodel, person= person)

        }


        //composable("Retrievepassword") { Retrieve() }
    }

}
