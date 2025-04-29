package com.awtech.cv_page

import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun StartPage(viewModel: ViewModel, person: Person) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var profileImageUri by remember { mutableStateOf<Uri?>(null) }
    var qrCodeBase64 by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    //var person = viewModel.editperson

    LaunchedEffect(true) {
        name = TextFieldValue(person.name)
        email = TextFieldValue(person.email)
    }

    val coroutineScope = rememberCoroutineScope()

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        profileImageUri = uri
    }

    Box(modifier = Modifier.fillMaxSize().padding(bottom = 16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            profileImageUri?.let { uri ->
                val context = LocalContext.current


                val bitmap by produceState<android.graphics.Bitmap?>(null, uri) {
                    value = withContext(Dispatchers.IO) {
                        try {
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
                    }
                }

                bitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .padding(top = 32.dp, bottom = 16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.width(IntrinsicSize.Max)
                ) {
                    Text(
                        text = name.text,
                        fontSize = 30.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Text(
                        text = email.text,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            if (profileImageUri == null) {
                Button(
                    onClick = { imagePickerLauncher.launch("image/*") },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Select Profile Image")
                }
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
                        contentDescription = "Contacts",
                        modifier = Modifier
                            .size(300.dp)
                            .padding(8.dp)
                    )
                }
            }

            errorMessage?.let {
                Text(it, color = androidx.compose.ui.graphics.Color.Red)
            }
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter),
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(16.dp),
            shadowElevation = 6.dp
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            isLoading = true

                            qrCodeBase64 = withContext(Dispatchers.IO) {
                                try {
                                    val vCardData = """
                                        BEGIN:VCARD
                                        VERSION:3.0
                                        N:${name.text}
                                        EMAIL:${email.text}
                                        END:VCARD
                                    """.trimIndent()
                                    QrCodeGenerator.generateQRCode(vCardData)
                                } catch (e: Exception) {
                                    errorMessage = "Error generating QR code: ${e.message}"
                                    null
                                }
                            }
                            isLoading = false
                        }
                    },
                    modifier = Modifier
                        .padding(start = 24.dp, bottom = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.QrCode,
                        contentDescription = "Generate QR Code",
                        modifier = Modifier.size(36.dp)
                    )
                }
            }
        }
    }
}