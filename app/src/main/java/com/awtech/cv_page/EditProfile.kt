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
fun EditProfile() {
    var Webpage by remember { mutableStateOf("") }
    var LinkedIn by remember { mutableStateOf("") }
    var Github by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var Number by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,

        ) {
        Text("Edit your profiile", style = MaterialTheme.typography.headlineSmall)

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            TextField(value =name, onValueChange = {it},
                label = {Text("name")} )

            Spacer(modifier = Modifier.height(20.dp))
            TextField(value =email, onValueChange = {it},
                label = {Text("email")} )

            Spacer(modifier = Modifier.height(20.dp))
            TextField(value =Webpage, onValueChange = {it},
                label = {Text("Webpage")} )

            Spacer(modifier = Modifier.height(20.dp))
            TextField(value =LinkedIn, onValueChange = {it},
                label = {Text("LinkedIn")} )

            Spacer(modifier = Modifier.height(20.dp))
            TextField(value =Github, onValueChange = {it},
                label = {Text("Github")} )

            Spacer(modifier = Modifier.height(20.dp))
            TextField(value =Number, onValueChange = {it},
                label = {Text("Number")} )

            Column(modifier = Modifier.padding(20.dp)
            ) {
                Text("Save", textAlign = TextAlign.Center,
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
fun MyEditPreview() {
    EditProfile()
}