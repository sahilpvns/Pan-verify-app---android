package com.innoventes.panverify.utils

import java.text.SimpleDateFormat
import java.util.Locale

object Validator {

    fun isValidPAN(pan: String): Boolean {

        val regex = Regex("[A-Z]{5}[0-9]{4}[A-Z]{1}")
        return regex.matches(pan)
    }

    fun isValidDate(
        day: String,
        month: String,
        year: String
    ): Boolean {

        if (day.length != 2 ||
            month.length != 2 ||
            year.length != 4
        ) {
            return false
        }

        return try {

            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

            formatter.isLenient = false

            formatter.parse("$day/$month/$year")

            true

        } catch (e: Exception) {
            false
        }
    }
}
