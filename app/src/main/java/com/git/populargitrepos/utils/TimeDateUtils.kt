package com.git.populargitrepos.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object TimeDateUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic
    fun convertUtcToLocalTime(timestamp: String?): String {
        if (timestamp.isNullOrEmpty()) return "N/A" // Handle null case

        return try {
            val instant = Instant.parse(timestamp) // Parse UTC time
            val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss")
                .withZone(ZoneId.systemDefault()) // Convert to local timezone

            formatter.format(instant)
        } catch (e: Exception) {
            "Invalid Date" // Handle parsing errors
        }
    }
}
