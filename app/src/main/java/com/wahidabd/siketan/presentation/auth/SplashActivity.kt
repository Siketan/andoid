package com.wahidabd.siketan.presentation.auth

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.siketan.R
import com.wahidabd.siketan.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun getViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {}

    override fun initObservers() {}

}