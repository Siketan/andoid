package com.wahidabd.siketan.utils

import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


/**
 * Created by Wahid on 6/8/2023.
 * Github github.com/wahidabd.
 */

val localeIndonesia = Locale("ID", "id")

fun Fragment.navDirection(direction: NavDirections) =
    findNavController().navigate(direction)

fun emailMatches(value: String): Boolean =
    !TextUtils.isEmpty(value) && Patterns.EMAIL_ADDRESS.matcher(value).matches()


fun Fragment.navigate(navDirections: NavDirections){
    findNavController().navigate(navDirections)
}

fun String.dateFormat(): String{
    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", localeIndonesia)
    val output = SimpleDateFormat("dd MMMM yyyy", localeIndonesia)
    val date = input.parse(this)
    return output.format(date)
}

fun Double.toCurrency(): String {
    val format = NumberFormat.getCurrencyInstance(localeIndonesia)
    return format.format(this)
}

