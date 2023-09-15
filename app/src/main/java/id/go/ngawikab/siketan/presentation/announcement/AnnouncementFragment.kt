package id.go.ngawikab.siketan.presentation.announcement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showSnackbarMessage
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.visible
import id.go.ngawikab.siketan.databinding.FragmentAnnouncementBinding
import id.go.ngawikab.siketan.presentation.announcement.adapter.AnnouncementAdapter
import id.go.ngawikab.siketan.presentation.announcement.adapter.EventAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnnouncementFragment : BaseFragment<FragmentAnnouncementBinding>() {

    private val viewModel: AnnouncementViewModel by viewModel()
    private val mAdapter by lazy { AnnouncementAdapter(requireContext()) }
    private val eventAdapter by lazy { EventAdapter(requireContext()) }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentAnnouncementBinding {
        return FragmentAnnouncementBinding.inflate(layoutInflater)
    }

    override fun initUI() {
        binding.rvInfo.adapter = mAdapter
        binding.rvEvent.adapter = eventAdapter
    }

    override fun initAction() {
        with(binding) {
            imgBack.onClick { findNavController().navigateUp() }
            rgContainer.setOnCheckedChangeListener { radioGroup, _ ->
                if (radioGroup.checkedRadioButtonId == rbNews.id) {
                    viewModel.infoTani()
                } else {
                    viewModel.event()
                }
            }
        }
    }

    override fun initProcess() {
        viewModel.infoTani()
    }

    override fun initObservers() {
        with(binding) {
            viewModel.infoTani.observerLiveData(
                viewLifecycleOwner,
                onLoading = {
                    progress.visible()
                    rvInfo.gone()
                    rvEvent.gone()
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
                    rvInfo.visible()
                    rvEvent.gone()
                    mAdapter.setData = it
                }
            )

            viewModel.event.observerLiveData(
                viewLifecycleOwner,
                onLoading = {
                    progress.visible()
                    rvEvent.gone()
                    rvInfo.gone()
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
                    rvInfo.gone()
                    rvEvent.visible()
                    eventAdapter.setData = it
                }
            )
        }
    }

}