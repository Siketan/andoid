package id.go.ngawikab.siketan.presentation.chat

import android.content.Context
import android.content.Intent
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.clear
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.setImageUrl
import com.wahidabd.library.utils.exts.toStringTrim
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.data.chat.model.request.ChatJoinRequest
import id.go.ngawikab.siketan.data.chat.model.request.ChatLatestPetaniRequest
import id.go.ngawikab.siketan.data.chat.model.request.ChatLatestRequest
import id.go.ngawikab.siketan.data.chat.model.request.ChatSendRequest
import id.go.ngawikab.siketan.databinding.ActivityChatRoomBinding
import id.go.ngawikab.siketan.presentation.chat.adapter.ChatRoomAdapter
import id.go.ngawikab.siketan.utils.Constant
import id.go.ngawikab.siketan.utils.PrefManager
import id.go.ngawikab.siketan.utils.UserRole
import id.go.ngawikab.siketan.utils.common.SiketanBaseActivity
import id.go.ngawikab.siketan.utils.onBackPress
import org.koin.android.ext.android.inject

class ChatRoomActivity : SiketanBaseActivity<ActivityChatRoomBinding>() {

    private lateinit var chatAdapter: ChatRoomAdapter
    private var initPrevMessageLoading = false

    private val viewModel: ChatViewModel by inject()
    private val pref: PrefManager by inject()
    private var partnerId = 0
    private var chatId = 0

    companion object {
        fun start(context: Context, id: Int? = 0) {
            context.startActivity(
                Intent(context, ChatRoomActivity::class.java)
                    .putExtra(Constant.RECEIVER_KEY, id)
            )
        }
    }

    override fun getViewBinding(): ActivityChatRoomBinding =
        ActivityChatRoomBinding.inflate(layoutInflater)

    override fun initUI() {

        partnerId = intent.getIntExtra(Constant.RECEIVER_KEY, 0)

        chatAdapter = ChatRoomAdapter(pref.getUser().id)
        binding.rvChat.adapter = chatAdapter
    }

    override fun initAction() {
        binding.imgBack.onClick { onBackPress() }
        binding.imgSend.onClick { sendMessage() }
    }

    override fun initProcess() {
        viewModel.onConnect()

        val user = pref.getUser()
        if (user.role == UserRole.PETANI.role) {
            val data = ChatLatestRequest(user.desa.toString(), user.id ?: 0)
            viewModel.getLatestMessages(data)
        } else if (user.role == UserRole.PENYULUH.role) {
            val data = ChatLatestPetaniRequest(user.id ?: 0, partnerId)
            viewModel.getLatestChatPetani(data)
        }
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
                binding.imgProfile.setImageUrl(
                    this,
                    it.user.foto.toString(),
                    R.drawable.placeholder,
                    true
                )
                chatAdapter.addPrevMessages(it.messages)

                partnerId = it.partnerId ?: 0
                chatId = it.chatId ?: 0
                val data = ChatJoinRequest(pref.getUser().id ?: 0, it.chatId ?: 0)
                viewModel.onJoin(data)
            }
        )
    }

    private fun sendMessage() {
        val message = binding.edtMessage.toStringTrim()
        val data = ChatSendRequest(
            fromUserId = pref.getUser().id ?: 0,
            toUserId = partnerId,
            message = message,
            chatId = chatId
        )
        viewModel.sendMessage(data)
        binding.edtMessage.clear()
    }

}