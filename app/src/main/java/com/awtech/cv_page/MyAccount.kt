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
fun MyAccount() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,

        ) {
        Text("My Account", style = MaterialTheme.typography.headlineSmall)

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            TextField(value =name, onValueChange = {it},
                label = {Text("name")} )

            Spacer(modifier = Modifier.height(20.dp))
            TextField(value =email, onValueChange = {it},
                label = {Text("email")} )

            Spacer(modifier = Modifier.height(20.dp))
            TextField(value =password, onValueChange = {it},
                label = {Text("password")} )

            Column(modifier = Modifier.padding(20.dp)
            ) {
                Text("Save changes", textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.clickable(onClick = {}))
            }
        }

        Column {
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MyPreview() {
    MyAccount()
}