package com.awtech.cv_page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SigninScreen(viewModel: ViewModel, createaccount: ()->Unit,
                 forgotpassword: ()->Unit, signin: (Person)->Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val listpersons by viewModel.personlistview.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        // App title
        Text(
            text = "MyCV App",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall
        )

        // Email and password inputs
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") }
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") }
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(10.dp)
            ) {
                Text("Forgot your password?",modifier = Modifier.clickable(onClick = {
                    forgotpassword()
                }))
                Text(
                    "Sign in",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.clickable(onClick = {
                        for (item in listpersons) {
                            if(item.email == email && item.password == password) {
                                signin(item)
                            }
                        }
                    })
                )
            }
        }

        Text("create an account",modifier = Modifier.clickable(onClick = {createaccount()}))


        // Bottom section with "forgot" and "signin"

    }
}



@Composable
@Preview(showBackground = true)
fun signinpreview() {
    SigninScreen(viewModel = viewModel(),createaccount={}, {}, {Person})
}