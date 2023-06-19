package com.wahidabd.siketan.utils

import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
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


fun Fragment.navigate(navDirections: NavDirections) {
    findNavController().navigate(navDirections)
}

fun String.dateFormat(): String {
    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", localeIndonesia)
    val output = SimpleDateFormat("dd MMMM yyyy", localeIndonesia)
    val date = input.parse(this)
    return output.format(date)
}

fun Double.toCurrency(): String {
    val format = NumberFormat.getCurrencyInstance(localeIndonesia)
    return format.format(this)
}

fun toRequestBody(data: List<Any?>): RequestBody =
    MultipartBody.Builder().setType(MultipartBody.FORM).apply {
        for (i in data) {
            if (i is File) {
                addFormDataPart(
                    "$i",
                    "${i.name}.jpg",
                    i.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                )
            } else {
                addFormDataPart("$i", i.toString())
            }
        }
    }.build()

