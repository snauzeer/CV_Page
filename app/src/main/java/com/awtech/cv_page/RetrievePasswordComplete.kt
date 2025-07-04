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
fun RetrievePasswordCompleteComplete(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Instructions to reset your password has been sent to your email. Check your inbox and follow the instructions. If you did not receive the email, please check your spam folder.",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )




        Spacer(modifier = Modifier.height(32.dp))

        FilledButtonExample(onClick = {},"text")

    }
}

@Preview(showBackground = true)
@Composable
fun RetrievePasswordPreview() {
    RetrievePasswordCompleteComplete(onClick={})
}


