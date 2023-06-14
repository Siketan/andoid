package com.wahidabd.siketan.presentation.auth

import android.content.Context
import android.content.Intent
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.siketan.databinding.ActivityAuthBinding

class AuthActivity : BaseActivity<ActivityAuthBinding>() {

    companion object{
        fun start(context: Context){
            context.startActivity(Intent(context, AuthActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityAuthBinding {
        return ActivityAuthBinding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {}

    override fun initObservers() {}

}