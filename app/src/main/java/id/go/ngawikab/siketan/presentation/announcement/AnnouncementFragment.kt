package id.go.ngawikab.siketan.presentation.announcement

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showSnackbarMessage
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.visible
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.databinding.FragmentAnnouncementBinding
import id.go.ngawikab.siketan.domain.farm.model.response.EventTani
import id.go.ngawikab.siketan.domain.farm.model.response.InfoTani
import id.go.ngawikab.siketan.presentation.announcement.adapter.AnnouncementAdapter
import id.go.ngawikab.siketan.presentation.announcement.adapter.EventAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnnouncementFragment : BaseFragment<FragmentAnnouncementBinding>() {

    private val viewModel: AnnouncementViewModel by viewModel()
    private val mAdapter by lazy { AnnouncementAdapter(requireContext(),{
        setOnClickDetailInfo(it)
    }) }
    private val eventAdapter by lazy { EventAdapter(requireContext(),{
        setOnClickDetailEvent(it)
    }) }

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
                viewModel.currentPage = 0
                viewModel.selectedRadioButtonId = radioGroup.checkedRadioButtonId
                viewModel.fetchData()
            }
            btnNext.onClick {
                viewModel.loadNextPage()
            }
            btnPrevious.onClick {
                viewModel.loadPreviousPage()
            }
        }
    }

    override fun initProcess() {
        with(binding){
            rbNews.performClick()
        }
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
                    mAdapter.setData = ArrayList(it)
                    updatePaginationButtons()
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
                    eventAdapter.setData = ArrayList(it)
                    updatePaginationButtons()
                }
            )
        }
    }

    private fun updatePaginationButtons() {
        val totalItems = if (viewModel.selectedRadioButtonId == R.id.rbNews) {
            viewModel.getInfoTaniSize()
        } else {
            viewModel.getEventTaniSize()
        }

        val totalPages = (totalItems + viewModel.pageSize - 1) / viewModel.pageSize
        val currentPage = viewModel.currentPage
        binding.btnPrevious.isEnabled = currentPage > 0
        binding.btnNext.isEnabled = currentPage < totalPages - 1
        updatePageButtons(currentPage, totalPages)
    }

    private fun updatePageButtons(currentPage: Int, totalPages: Int) {
        binding.paginationContainer.removeAllViews()

        val startPage: Int
        val endPage: Int

        when {
            totalPages <= 3 -> {
                startPage = 0
                endPage = totalPages - 1
            }
            currentPage <= 1 -> {
                startPage = 0
                endPage = 2
            }
            currentPage >= totalPages - 2 -> {
                startPage = totalPages - 3
                endPage = totalPages - 1
            }
            else -> {
                startPage = currentPage - 1
                endPage = currentPage + 1
            }
        }

        for (i in startPage..endPage) {
            val pageButton = Button(requireContext()).apply {
                text = (i + 1).toString()
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0,0,0,0)
                }
                setTextColor(ContextCompat.getColor(context, R.color.white))
                if (i == currentPage) {
                    setBackgroundResource(R.drawable.pagination_button_selected)
                } else {
                    setBackgroundResource(R.drawable.pagination_button_bg)
                }

                setOnClickListener {
                    viewModel.currentPage = i
                    if (viewModel.selectedRadioButtonId == R.id.rbNews) {
                        viewModel.updateInfoTani()
                    } else {
                        viewModel.updateEventTani()
                    }
                    updatePaginationButtons()
                }
            }
            binding.paginationContainer.addView(pageButton)
        }
    }

    private fun setOnClickDetailEvent(data: EventTani){
        findNavController().navigate(AnnouncementFragmentDirections.actionAnnouncementFragmentToEventDetailFragment(data))
    }

    private fun setOnClickDetailInfo(data: InfoTani){
        findNavController().navigate(AnnouncementFragmentDirections.actionAnnouncementFragmentToAnnouncementDetailFragment(data))

    }
}