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
import androidx.compose.material3.Button


@Composable
fun DeleteAccountComplete(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Are you sure you want delete your account?",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Click on the button below to confirm.",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )




        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onClick() },
            shape = MaterialTheme.shapes.large
        ) {
            Text("Confirm Delete account")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteAccountCompletePreview() {
    DeleteAccountComplete(onClick={})
}


