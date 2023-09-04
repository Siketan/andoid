package com.wahidabd.siketan.utils.common

import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.siketan.R


/**
 * Created by Wahid on 8/27/2023.
 * Github github.com/wahidabd.
 */


abstract class SiketanBaseActivity <VB: ViewBinding>: BaseActivity<VB>() {

    private var loadingDialog: AlertDialog? = null

    fun showDialogLoading() {
        loadingDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
            loadingDialog = null
        }

        loadingDialog = MaterialAlertDialogBuilder(this)
            .setView(R.layout.layout_dialog_loading)
            .setCancelable(false)
            .create()
            .apply {
                this.window?.let { window ->
                    window.setDimAmount(0.75F)
                    window.setBackgroundDrawableResource(android.R.color.transparent)
                    this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
                }
            }

        loadingDialog?.show()
    }

    fun hideDialogLoading() {
        loadingDialog?.hide()
        loadingDialog?.cancel()
    }

}