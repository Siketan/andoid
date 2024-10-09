package id.go.ngawikab.siketan.presentation.chat

import android.content.Context
import android.content.Intent
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.onClick
import id.go.ngawikab.siketan.databinding.ActivityChatBinding
import id.go.ngawikab.siketan.presentation.chat.adapter.ChatUserAdapter
import id.go.ngawikab.siketan.utils.PrefManager
import id.go.ngawikab.siketan.utils.common.SiketanBaseActivity
import id.go.ngawikab.siketan.utils.onBackPress
import org.koin.android.ext.android.inject


class ChatActivity : SiketanBaseActivity<ActivityChatBinding>() {

    private val pref: PrefManager by inject()
//    private val viewModel: ChatRoomViewModel by inject()

    private val chatUserAdapter by lazy {
        ChatUserAdapter(this, onItemClick = {
//            ChatRoomActivity.start(this, it)
//            showToast("Dalam pengembangan!")
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
        showToast("Dalam pengembangan!")
        binding.imgBack.onClick { onBackPress() }
    }

    override fun initProcess() {
    }

    override fun initObservers() {
//        viewModel.getUserChat.observerLiveData(
//            this,
//            onLoading = {
//                showDialogLoading()
//            },
//            onEmpty = {
//                hideDialogLoading()
//                showToast("Tidak ada data!")
//            },
//            onFailure = { _, mes ->
//                hideDialogLoading()
//                showToast(mes.toString())
//            },
//            onSuccess = {
//                hideDialogLoading()
//                chatUserAdapter.setData = it.petani
//            }
//        )
    }
}