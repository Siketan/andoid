package com.wahidabd.siketan.presentation.chat

import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.siketan.databinding.ActivityChatBinding


class ChatActivity : BaseActivity<ActivityChatBinding>() {

    override fun getViewBinding(): ActivityChatBinding {
        return ActivityChatBinding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {}

    override fun initObservers() {}

}