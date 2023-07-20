package com.wahidabd.siketan.utils

import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.wahidabd.siketan.R


/**
 * Created by Wahid on 7/4/2023.
 * Github github.com/wahidabd.
 */


fun Fragment.datePicker(
    onPositiveClick: ((Long) -> Unit)
){
    val material = MaterialDatePicker.Builder.datePicker()
    material.setTitleText("Pilih tanggal")
    material.setTheme(R.style.ThemeOverlay_App_DatePicker)
    val datePicker = material.build()
    datePicker.show(parentFragmentManager, "Date Picker")
    datePicker.addOnPositiveButtonClickListener {
        onPositiveClick.invoke(it)
    }
}