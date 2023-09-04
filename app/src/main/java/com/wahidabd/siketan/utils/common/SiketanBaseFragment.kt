package com.wahidabd.siketan.utils.common

import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.siketan.R


/**
 * Created by Wahid on 8/17/2023.
 * Github github.com/wahidabd.
 */


abstract class SiketanBaseFragment <VB: ViewBinding> : BaseFragment<VB>() {

    private var loadingDialog: AlertDialog? = null

    fun showLoading(){
        loadingDialog?.let {
            if (it.isShowing){it.dismiss()}
            loadingDialog = null
        }

        loadingDialog = MaterialAlertDialogBuilder(requireContext())
            .setView(R.layout.layout_dialog_loading)
            .setCancelable(false)
            .create()
            .apply {
                window?.setBackgroundDrawableResource(android.R.color.transparent)
                window?.setDimAmount(0.75F)
                supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
            }

        loadingDialog?.show()
    }

    fun hideLoading(){
        loadingDialog?.hide()
        loadingDialog?.cancel()
        loadingDialog?.dismiss()
    }

}