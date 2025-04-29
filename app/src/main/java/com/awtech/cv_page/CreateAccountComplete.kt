package com.awtech.cv_page

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height



@Composable
fun CreateAccountComplete(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Your account is now created!",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Return to the login page and login with your new account.",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )



        Spacer(modifier = Modifier.height(32.dp))

        FilledButtonExample(onClick = {}, "Return to login")


    }
}

@Preview(showBackground = true)
@Composable
fun CreateAccountPreview() {
    CreateAccountComplete(onClick={})
}


