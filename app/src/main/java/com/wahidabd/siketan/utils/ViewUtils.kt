package com.wahidabd.siketan.utils

import android.app.Dialog
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.wahidabd.siketan.databinding.LayoutLoadingBinding


/**
 * Created by Wahid on 6/8/2023.
 * Github github.com/wahidabd.
 */


fun Fragment.navDirection(direction: NavDirections) =
    findNavController().navigate(direction)