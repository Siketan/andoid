package id.go.ngawikab.siketan.presentation.store

import android.view.LayoutInflater
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
import id.go.ngawikab.siketan.databinding.FragmentStoreBinding
import id.go.ngawikab.siketan.presentation.store.viewmodel.StoreViewModel
import id.go.ngawikab.siketan.utils.navigate
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.wahidabd.library.utils.exts.onClick as onClick1

class StoreFragment : BaseFragment<FragmentStoreBinding>() {

    private val viewModel: StoreViewModel by viewModel()
    private lateinit var pagingAdapter: StorePagingAdapter

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentStoreBinding {
        return FragmentStoreBinding.inflate(layoutInflater)
    }

    override fun initUI() {
        pagingAdapter = StorePagingAdapter(requireContext())
        binding.rvStore.apply {
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
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.products.collectLatest {
                    pagingAdapter.submitData(it)
                }
            }
        }
    }

    private fun loadState(){
        pagingAdapter.addLoadStateListener { loadState ->
            binding.apply {
                rvStore.isVisible = loadState.source.refresh is LoadState.NotLoading
                progress.isVisible = loadState.source.refresh is LoadState.Loading

                rvStore.isVisible = !(loadState.source.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached && pagingAdapter.itemCount < 1)
            }
        }
    }

    override fun initAction() {
        binding.apply {
            imgBack.onClick1 { findNavController().navigateUp() }
            imgMyStore.onClick1 {
                navigate(StoreFragmentDirections.actionStoreFragmentToStoreAddFragment())
            }
        }
    }

    override fun initProcess() {
//        viewModel.product()
    }

    override fun initObservers() {
//        with(binding) {
//            viewModel.products.observerLiveData(
//                viewLifecycleOwner,
//                onLoading = {
//                    progress.visible()
//                },
//                onEmpty = {
//                    progress.gone()
//                    showSnackbarMessage(root, "Data tidak ditemukan")
//                },
//                onFailure = { _, m ->
//                    progress.gone()
//                    showSnackbarMessage(root, m.toString())
//                },
//                onSuccess = {
//                    progress.gone()
//                    rvStore.visible()
//                    storeAdapter.setData = it
//                }
//            )
//        }
    }

}