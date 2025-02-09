package com.git.populargitrepos.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object TimeDateUtils {
    @JvmStatic
    fun convertUtcToLocalTime(timestamp: String?): String {
        if (timestamp.isNullOrEmpty()) return "N/A" // Handle null case

        return try {
            // For API 26 and above
            val instant = Instant.parse(timestamp) // Parse UTC time
            val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss")
                .withZone(ZoneId.systemDefault()) // Convert to local timezone
            formatter.format(instant)
        } catch (e: Exception) {
            "Invalid Date" // Handle parsing errors
        }
    }
}
