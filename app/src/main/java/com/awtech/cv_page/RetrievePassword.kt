package com.awtech.cv_page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Retrieve() {
    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text("MyCV App", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(100.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.height(200.dp)

        ) {
            Column {
                Text("Type in your email to reset password")
                TextField(value = email,
                    onValueChange = {email = it},
                    label = { Text("name@gmail.com")})
            }

            Column {
                Text("Send",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.clickable(onClick = {}))
            }

        }



    }
}


@Composable
@Preview(showBackground = true)
fun PreviewRetrieve() {
    Retrieve()
}