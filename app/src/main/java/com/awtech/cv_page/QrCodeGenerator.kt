package com.awtech.cv_page

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import java.io.ByteArrayOutputStream

object QrCodeGenerator {

    fun generateQRCode(data: String): String {
        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 512, 512)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE)
            }
        }
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
    }
}


@Composable
fun QRCodePreview(data: String) {
    val qrCodeBase64 = QrCodeGenerator.generateQRCode(data)
    val qrCodeBytes = Base64.decode(qrCodeBase64, Base64.DEFAULT)
    val qrCodeBitmap = BitmapFactory.decodeByteArray(qrCodeBytes, 0, qrCodeBytes.size)
    Image(bitmap = qrCodeBitmap.asImageBitmap(), contentDescription = "QR Code")
}

@Composable
@Preview(showBackground = true)
fun PreviewQRCode() {
    QRCodePreview(data = "Hello, QR Code!")
}