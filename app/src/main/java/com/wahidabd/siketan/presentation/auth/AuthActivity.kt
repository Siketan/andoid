package com.wahidabd.siketan.presentation.auth

import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.siketan.databinding.ActivityAuthBinding

class AuthActivity : BaseActivity<ActivityAuthBinding>() {


    override fun getViewBinding(): ActivityAuthBinding {
        return ActivityAuthBinding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {}

    override fun initObservers() {}

}