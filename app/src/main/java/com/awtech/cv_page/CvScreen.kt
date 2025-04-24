package com.awtech.cv_page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp


@Composable
fun Personinfo(viewmodel: ViewModel) {

    var name by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var phonenumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var testQrCode by remember { mutableStateOf<Bitmap?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Enter your details to generate a QR code")

        // Input field for name
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        TextField(
            value = lastname,
            onValueChange = { lastname = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
        TextField(
            value = phonenumber,
            onValueChange = { phonenumber = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        // Input field for email
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )


        // Button to save data and generate QR code
        Button(
            onClick = {
                viewmodel.addperson(
                    name = name,
                    lastname = lastname,
                    phonenumber = phonenumber,
                    password = 123, // Replace with actual password logic
                    email = email
                )
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Save to Database")
        }

        // Button to generate QR code
        Button(
            onClick = {
                val vCard = """
                    BEGIN:VCARD
                    VERSION:3.0
                    N:$name
                    EMAIL:$email
                    END:VCARD
                """.trimIndent()
                val qrCodeBase64 = QrCodeGenerator.generateQRCode(vCard)
                testQrCode = decodeQRCode(qrCodeBase64)
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Generate QR Code")
        }

        // Display the generated QR code
        testQrCode?.let { bitmap ->
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Generated QR Code",
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )
        }
    }
}


// Function to decode Base64 QR code into a Bitmap
fun decodeQRCode(base64: String): Bitmap {
    val decodedBytes = Base64.decode(base64, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
}
// komment
/*
@Composable
fun Personinfo(viewmodel: ViewModel) {

    val listpersons by viewmodel.personlistview.collectAsState()
    Column {
        Text("hello")

        Button(onClick = {viewmodel.addperson("ahmad", "123", "wello.com")}) {

            Text("Add")

        }

        Button(onClick = {viewmodel.load()}) { Text("load")}

        LazyColumn {
            items(listpersons) {
                item -> Text(item.name)
            }
        }
    }
}*/