package com.wahidabd.siketan.utils

import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController


/**
 * Created by Wahid on 6/8/2023.
 * Github github.com/wahidabd.
 */


fun Fragment.navDirection(direction: NavDirections) =
    findNavController().navigate(direction)

fun emailMatches(value: String): Boolean =
    !TextUtils.isEmpty(value) && Patterns.EMAIL_ADDRESS.matcher(value).matches()


fun Fragment.navigate(navDirections: NavDirections){
    findNavController().navigate(navDirections)
}