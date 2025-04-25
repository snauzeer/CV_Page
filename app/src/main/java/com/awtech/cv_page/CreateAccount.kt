package com.awtech.cv_page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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

@Composable
fun CreateAccount(viewModel: ViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var emailexist by remember { mutableStateOf("")}
    var passwordexist by remember { mutableStateOf("")}
    var nameexist by remember { mutableStateOf("")}
    var emailcheck = false

    val listpersons by viewModel.personlistview.collectAsState()


    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,

        ) {
        Text("MyCV App Create Account", style = MaterialTheme.typography.headlineSmall)

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(emailexist)
            TextField(value =email, onValueChange = {it},
                label = {Text("email")} )

            Spacer(modifier = Modifier.height(20.dp))
            TextField(value =password, onValueChange = {it},
                label = {Text("password")} )

            Spacer(modifier = Modifier.height(20.dp))
            TextField(value =name, onValueChange = {it},
                label = {Text("name")} )

            Column(modifier = Modifier.padding(20.dp)
                    ) {
                Text("Create account", textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.clickable(onClick = {
                        for (item in listpersons) {
                            if (item.email !== email) {
                                emailcheck = true
                            }
                        }
                        if (emailcheck==false) {
                            emailexist = "this email exist"
                        }
                        if(password =="") {
                            passwordexist = "the password can not be empty"
                        }

                        if(name =="") {
                            nameexist = "the name can not be empty"
                        }
                        if(emailcheck && name!=="" && password!=="") {
                            emailcheck =true // type in a destionation
                        }
                    }))
            }
        }

        Column {
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CreatePreview() {
    CreateAccount(ViewModel())
}