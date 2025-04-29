package com.awtech.cv_page


import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun StartPage() {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var profileImageUri by remember { mutableStateOf<Uri?>(null) }
    var qrCodeBase64 by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        profileImageUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Create vCard QR Code", fontSize = 20.sp, modifier = Modifier.padding(8.dp))

        BasicTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    if (name.text.isEmpty()) Text("Name")
                    innerTextField()
                }
            }
        )

        BasicTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    if (email.text.isEmpty()) Text("Email")
                    innerTextField()
                }
            }
        )

        Button(
            onClick = { imagePickerLauncher.launch("image/*") },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Select Profile Image")
        }

        profileImageUri?.let { uri ->
            val context = LocalContext.current
            val bitmap = try {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                    val source = android.graphics.ImageDecoder.createSource(context.contentResolver, uri)
                    android.graphics.ImageDecoder.decodeBitmap(source)
                } else {
                    MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                }
            } catch (e: Exception) {
                errorMessage = "Error loading image: ${e.message}"
                null
            }

            bitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(8.dp)
                )
            }
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    isLoading = true
                    val vCardData = """
                        BEGIN:VCARD
                        VERSION:3.0
                        N:${name.text}
                        EMAIL:${email.text}
                        END:VCARD
                    """.trimIndent()

                    qrCodeBase64 = withContext(Dispatchers.IO) {
                        QrCodeGenerator.generateQRCode(vCardData)
                    }
                    isLoading = false
                }
            },
            modifier = Modifier.padding(8.dp),
            enabled = !isLoading
        ) {
            Text(if (isLoading) "Generating...wait" else "Generate QR Code")
        }

        qrCodeBase64?.let { base64 ->
            val qrCodeBitmap = try {
                val qrCodeBytes = Base64.decode(base64, Base64.DEFAULT)
                BitmapFactory.decodeByteArray(qrCodeBytes, 0, qrCodeBytes.size)
            } catch (e: Exception) {
                errorMessage = "Error displaying QR code: ${e.message}"
                null
            }

            qrCodeBitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Generated QR Code",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(8.dp)
                )
            }
        }

        errorMessage?.let {
            Text(it, color = androidx.compose.ui.graphics.Color.Red)
        }
    }
}