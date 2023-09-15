package com.wahidabd.siketan.presentation.chat

import android.content.Context
import android.content.Intent
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.siketan.databinding.ActivityChatBinding
import com.wahidabd.siketan.presentation.chat.adapter.ChatUserAdapter
import com.wahidabd.siketan.utils.PrefManager
import com.wahidabd.siketan.utils.common.SiketanBaseActivity
import com.wahidabd.siketan.utils.onBackPress
import org.koin.android.ext.android.inject


class ChatActivity : SiketanBaseActivity<ActivityChatBinding>() {

    private val pref: PrefManager by inject()
    private val viewModel: ChatViewModel by inject()

    private val chatUserAdapter by lazy {
        ChatUserAdapter(this, onItemClick = {
            ChatRoomActivity.start(this, it)
        })
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ChatActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityChatBinding {
        return ActivityChatBinding.inflate(layoutInflater)
    }

    override fun initUI() {
        binding.rvChat.adapter = chatUserAdapter
    }

    override fun initAction() {
        binding.imgBack.onClick { onBackPress() }
    }

    override fun initProcess() {
        viewModel.getPetaniChat(pref.getUser().id ?: 0)
    }

    override fun initObservers() {
        viewModel.getUserChat.observerLiveData(
            this,
            onLoading = {
                showDialogLoading()
            },
            onEmpty = {
                hideDialogLoading()
                showToast("Tidak ada data!")
            },
            onFailure = { _, mes ->
                hideDialogLoading()
                showToast(mes.toString())
            },
            onSuccess = {
                hideDialogLoading()
                chatUserAdapter.setData = it.petani
            }
        )
    }

}