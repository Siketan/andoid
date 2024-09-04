package id.go.ngawikab.siketan.presentation.chat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.exts.onClick
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.databinding.FragmentChatBinding
import id.go.ngawikab.siketan.databinding.FragmentStoreBinding
import id.go.ngawikab.siketan.presentation.chat.adapter.ChatPagingAdapter
import id.go.ngawikab.siketan.presentation.store.StoreFragmentDirections
import id.go.ngawikab.siketan.presentation.store.StoreLoadStateAdapter
import id.go.ngawikab.siketan.presentation.store.StorePagingAdapter
import id.go.ngawikab.siketan.presentation.store.viewmodel.StoreViewModel
import id.go.ngawikab.siketan.utils.PrefManager
import id.go.ngawikab.siketan.utils.navigate
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : BaseFragment<FragmentChatBinding>() {

    private val pref: PrefManager by inject()
    private val viewModel: ChatViewModel by viewModel()
    private lateinit var pagingAdapter: ChatPagingAdapter

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentChatBinding {
        return FragmentChatBinding.inflate(layoutInflater)
    }

    override fun initUI() {
        pagingAdapter = ChatPagingAdapter(requireContext(), pref.getUser())
        binding.rvChat.apply {
            adapter = pagingAdapter.withLoadStateHeaderAndFooter(
                header = StoreLoadStateAdapter{pagingAdapter.retry()},
                footer = StoreLoadStateAdapter{pagingAdapter.retry()}
            )
            layoutManager = GridLayoutManager(requireContext(), 2)
            itemAnimator = DefaultItemAnimator()
        }


        subscribe()
        loadState()
    }
    private fun subscribe(){
        lifecycleScope.launch {
            try {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.users(pref.getUser().id!!).collectLatest {
                        pagingAdapter.submitData(it)
                    }
                }
            } catch (e: Exception) {
                Log.e("ChatFragment", "Error in subscribe: ${e.message}")
            }
        }
    }

    private fun loadState(){
        pagingAdapter.addLoadStateListener { loadState ->
            binding.apply {
                rvChat.isVisible = loadState.source.refresh is LoadState.NotLoading
                progress.isVisible = loadState.source.refresh is LoadState.Loading

                rvChat.isVisible = !(loadState.source.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached && pagingAdapter.itemCount < 1)
            }
        }
    }

    override fun initAction() {
        binding.apply {
            imgBack.onClick { findNavController().navigateUp() }
        }
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }
}