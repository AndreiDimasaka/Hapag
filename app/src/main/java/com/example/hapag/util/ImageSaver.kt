package com.example.hapag.util

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import java.io.File
import java.io.FileOutputStream

@Composable
fun saveImageToStorage(context: Context, uri: Uri?): String? {
    return try {
        // Create userimages directory if it doesn't exist
        val userImagesDir = File(context.filesDir, "userimages")

        // Generate unique filename
        val filename = "IMG_${System.currentTimeMillis()}.png"
        val outputFile = File(userImagesDir, filename)

        // Copy image to destination
        if (uri != null) {
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                FileOutputStream(outputFile).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        }
        else{
            return null
        }

        "userimages/$filename"
    } catch (e: Exception) {
        Log.e("ImageSaver", "Error saving image", e)
        null
    }
}