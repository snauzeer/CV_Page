package com.awtech.cv_page
import androidx. compose. runtime. Composable
import androidx.compose.material3.Button
import androidx.compose.material3.Text


@Composable
fun FilledButtonExample(onClick: () -> Unit, text: String) {
    Button(onClick = { onClick() }) {
        Text(text)
    }
}