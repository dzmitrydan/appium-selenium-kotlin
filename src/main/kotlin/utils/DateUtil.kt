package utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun getCurrentDate(): String {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("MMMM d", Locale.getDefault())
    return currentDate.format(formatter)
}