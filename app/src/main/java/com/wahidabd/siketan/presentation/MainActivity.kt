package com.wahidabd.siketan.presentation

import android.content.Context
import android.content.Intent
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.permission.requestPermission
import com.wahidabd.library.utils.permission.showPermissionDialog
import com.wahidabd.siketan.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    companion object {
        fun start(context: Context){
            context.startActivity(Intent(context, MainActivity::class.java))
        }

        const val CAMERA_REQUEST_CODE = 1112
        const val GALLERY_REQUEST_CODE = 1113
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {
        requestPermission(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            onAllowed = {},
            onDenied = { permission ->
                when(permission){
                    android.Manifest.permission.CAMERA -> showPermissionDialog(
                        permission,
                        CAMERA_REQUEST_CODE
                    )
                    android.Manifest.permission.READ_EXTERNAL_STORAGE -> showPermissionDialog(
                        permission,
                        GALLERY_REQUEST_CODE
                    )
                }
            },
            onNeedPermissionRationale = {permission ->
                when(permission){
                    android.Manifest.permission.CAMERA -> showPermissionDialog(
                        permission,
                        CAMERA_REQUEST_CODE
                    )
                    android.Manifest.permission.READ_EXTERNAL_STORAGE -> showPermissionDialog(
                        permission,
                        GALLERY_REQUEST_CODE
                    )
                }
            }
        )
    }

    override fun initObservers() {}

}