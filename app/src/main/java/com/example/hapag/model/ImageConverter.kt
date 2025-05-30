package com.example.hapag.model

import android.net.Uri
import androidx.room.TypeConverter
import com.example.hapag.model.data.ImageData

object ImageConverter {
    @TypeConverter
    fun fromImageData(imageData: ImageData?): String {
        return when (imageData) {
            is ImageData.DrawableRes -> "DRAWABLE|${imageData.resId}"
            is ImageData.UriVal -> "URI|${imageData.uri.toString()}"
            ImageData.Blank -> "BLANK"
            null -> "NULL"
        }
    }

    @TypeConverter
    fun toImageData(value: String?): ImageData? {
        return when {
            value == null || value == "NULL" -> null
            value.startsWith("DRAWABLE|") -> {
                val resId = value.removePrefix("DRAWABLE|").toIntOrNull()
                resId?.let { ImageData.DrawableRes(it) }
            }
            value.startsWith("URI|") -> {
                val uriString = value.removePrefix("URI|")
                if (uriString.isNotEmpty()) Uri.parse(uriString)?.let { ImageData.UriVal(it) } else ImageData.Blank
            }
            else -> ImageData.Blank
        }
    }
}