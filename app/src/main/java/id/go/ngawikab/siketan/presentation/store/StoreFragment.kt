package id.go.ngawikab.siketan.presentation.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showSnackbarMessage
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.visible
import id.go.ngawikab.siketan.databinding.FragmentStoreBinding
import id.go.ngawikab.siketan.presentation.store.viewmodel.StoreViewModel
import id.go.ngawikab.siketan.utils.navigate
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoreFragment : BaseFragment<FragmentStoreBinding>() {

    private val viewModel: StoreViewModel by viewModel()
    private val storeAdapter by lazy {
        StoreAdapter(requireContext())
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentStoreBinding {
        return FragmentStoreBinding.inflate(layoutInflater)
    }

    override fun initUI() {
        binding.rvStore.adapter = storeAdapter
    }

    override fun initAction() {
        binding.apply {
            imgBack.onClick { findNavController().navigateUp() }
            imgMyStore.onClick {
                navigate(StoreFragmentDirections.actionStoreFragmentToStoreAddFragment())
            }
        }
    }

    override fun initProcess() {
        viewModel.product()
    }

    override fun initObservers() {
        with(binding) {
            viewModel.products.observerLiveData(
                viewLifecycleOwner,
                onLoading = {
                    progress.visible()
                },
                onEmpty = {
                    progress.gone()
                    showSnackbarMessage(root, "Data tidak ditemukan")
                },
                onFailure = { _, m ->
                    progress.gone()
                    showSnackbarMessage(root, m.toString())
                },
                onSuccess = {
                    progress.gone()
                    rvStore.visible()
                    storeAdapter.setData = it
                }
            )
        }
    }

}