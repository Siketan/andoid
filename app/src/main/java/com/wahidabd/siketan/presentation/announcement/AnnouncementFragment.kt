package com.wahidabd.siketan.presentation.announcement

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showSnackbarMessage
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.visible
import com.wahidabd.siketan.databinding.FragmentAnnouncementBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnnouncementFragment : BaseFragment<FragmentAnnouncementBinding>() {

    private val viewModel: AnnouncementViewModel by viewModel()
    private val mAdapter by lazy {
        AnnouncementAdapter(requireContext())
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentAnnouncementBinding {
        return FragmentAnnouncementBinding.inflate(layoutInflater)
    }

    override fun initUI() {
        binding.rvInfo.adapter = mAdapter
    }

    override fun initAction() {}

    override fun initProcess() {
        viewModel.infoTani()
    }

    override fun initObservers() {
        with(binding){
            viewModel.infoTani.observerLiveData(
                viewLifecycleOwner,
                onLoading = {
                    progress.visible()
                    rvInfo.gone()
                },
                onEmpty = {
                    progress.gone()
                    showSnackbarMessage(root, "Data tidak ditemukan")
                },
                onFailure = {t, m ->
                    progress.gone()
                    showSnackbarMessage(root, m.toString())
                },
                onSuccess = {
                    progress.gone()
                    rvInfo.visible()
                    mAdapter.setData = it
                }
            )
        }
    }

}