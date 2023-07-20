package com.wahidabd.siketan.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.text.DateFormatSymbols
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


/**
 * Created by Wahid on 6/8/2023.
 * Github github.com/wahidabd.
 */

val localeIndonesia = Locale("ID", "id")
val listMonth = DateFormatSymbols(localeIndonesia).months.toList()

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

fun Long.toTimeStamp(): String {
    val date = Date(this)
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", localeIndonesia)
    return format.format(date)
}

fun Long.toDateString(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd MMMM yyyy", localeIndonesia)
    return format.format(date)
}

fun Long.toDateApi(): String {
    val date = Date(this)
    val format = SimpleDateFormat("yyyy-MM-dd", localeIndonesia)
    return format.format(date)
}

fun Double.toCurrency(): String {
    val format = NumberFormat.getCurrencyInstance(localeIndonesia)
    return format.format(this)
}

fun convertToRequestBody(vararg data: Any?): RequestBody =
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


val timeStamp: String = SimpleDateFormat(
    "dd-MMM-yyyy",
    localeIndonesia
).format(System.currentTimeMillis())

fun createCustomTempFile(context: Context): File {
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(timeStamp, ".jpg", storageDir)
}

fun uriToFile(selectedImg: Uri, context: Context): File {
    val contentResolver: ContentResolver = context.contentResolver
    val myFile = createCustomTempFile(context)

    val inputStream = contentResolver.openInputStream(selectedImg) as InputStream
    val outputStream: OutputStream = FileOutputStream(myFile)
    val buf = ByteArray(1024)
    var len: Int
    while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
    outputStream.close()
    inputStream.close()

    return myFile
}

fun Fragment.selectDate(
    positiveText: String = "PILIH",
    negativeText: String = "BATAL",
    positiveButton: ((String) -> Unit),
    negativeButton: ((String) -> Unit)
) {
    val datePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Pilih tanggal")
        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
        .setPositiveButtonText(positiveText)
        .setNegativeButtonText(negativeText)

}
