package com.wahidabd.siketan.presentation.chat

import android.content.Context
import android.content.Intent
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.siketan.databinding.ActivityChatBinding
import org.koin.android.ext.android.inject


class ChatActivity : BaseActivity<ActivityChatBinding>() {


    companion object{
        fun start(context: Context){
            context.startActivity(Intent(context, ChatActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityChatBinding {
        return ActivityChatBinding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {
    }

    override fun initObservers() {}

}