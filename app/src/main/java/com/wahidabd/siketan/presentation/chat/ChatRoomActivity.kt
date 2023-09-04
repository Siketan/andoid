package com.wahidabd.siketan.presentation.chat

import android.content.Context
import android.content.Intent
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.siketan.databinding.ActivityChatRoomBinding
import com.wahidabd.siketan.utils.PrefManager
import com.wahidabd.siketan.utils.common.SiketanBaseActivity
import com.wahidabd.siketan.data.chat.model.request.ChatJoinRequest
import com.wahidabd.siketan.data.chat.model.request.ChatLatestRequest
import com.wahidabd.siketan.presentation.chat.adapter.ChatRoomAdapter
import org.koin.android.ext.android.inject

class ChatRoomActivity : SiketanBaseActivity<ActivityChatRoomBinding>() {

    private lateinit var chatAdapter: ChatRoomAdapter
    private var initPrevMessageLoading = false

    private val viewModel: ChatViewModel by inject()
    private val pref: PrefManager by inject()

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ChatRoomActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityChatRoomBinding =
        ActivityChatRoomBinding.inflate(layoutInflater)

    override fun initUI() {
        chatAdapter = ChatRoomAdapter(pref.getUser().id)
        binding.rvChat.adapter = chatAdapter
    }

    override fun initAction() {}

    override fun initProcess() {
        val user = pref.getUser()
        val data = ChatLatestRequest(user.desa.toString(), user.id ?: 0)
        viewModel.onConnect()
        viewModel.getLatestMessages(data)
    }

    override fun initObservers() {
        viewModel.getLatestMessages.observerLiveData(
            this,
            onLoading = {
                showDialogLoading()
            },
            onEmpty = {
                hideDialogLoading()
            },
            onFailure = { _, message ->
                hideDialogLoading()
                showToast(message.toString())
            },
            onSuccess = {
                hideDialogLoading()

                binding.tvName.text = it.user.nama
                chatAdapter.addPrevMessages(it.messages)

                val data = ChatJoinRequest(pref.getUser().id ?: 0, it.chatId ?: 0)
                viewModel.onJoin(data)
            }
        )
    }

}