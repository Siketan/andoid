package id.go.ngawikab.siketan.presentation.journal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showSnackbarMessage
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.visible
import com.wahidabd.library.utils.exts.visibleIf
import id.go.ngawikab.siketan.data.farm.model.journal.Jounal
import id.go.ngawikab.siketan.databinding.FragmentJournalBinding
import id.go.ngawikab.siketan.domain.farm.model.response.EventTani
import id.go.ngawikab.siketan.domain.farm.model.response.Journal
import id.go.ngawikab.siketan.presentation.announcement.AnnouncementFragmentDirections
import id.go.ngawikab.siketan.presentation.announcement.adapter.EventAdapter
import id.go.ngawikab.siketan.presentation.journal.adapter.JournalAdapter
import id.go.ngawikab.siketan.utils.PrefManager
import id.go.ngawikab.siketan.utils.UserRole
import id.go.ngawikab.siketan.utils.navigate
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class JournalFragment : BaseFragment<FragmentJournalBinding>() {

    private val viewModel: JournalViewModel by viewModel()
    private val journalAdapter by lazy {
        JournalAdapter(requireContext(),
            {
            setOnClickDetailJournal(it)
        }
        )
    }

    private val pref: PrefManager by inject()
    private var reveal: Boolean = true

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentJournalBinding =
        FragmentJournalBinding.inflate(layoutInflater)

    override fun initUI() {
        with(binding) {
            fabAdd.visibleIf { pref.getUser().role == UserRole.PENYULUH.role }
        }
    }

    override fun initAction() {
        with(binding) {
            imgBack.onClick { findNavController().navigateUp() }
            fabAdd.onClick {
                fabAdd.isExpanded = reveal
                reveal = !reveal
            }

            linearBottom.onClick { navigate(JournalFragmentDirections.actionJournalFragmentToJournalAddFragment()) }
            linearTop.onClick { navigate(JournalFragmentDirections.actionJournalFragmentToPresensiFragment()) }
        }
    }

    override fun initProcess() {
        binding.rvStore.adapter = journalAdapter
        viewModel.get()
    }

    override fun initObservers() {
        with(binding) {
            viewModel.get.observerLiveData(
                viewLifecycleOwner,
                onEmpty = {
                    progress.gone()
                },
                onLoading = {
                    progress.visible()
                },
                onFailure = { _, m ->
                    progress.gone()
                    showSnackbarMessage(root, m.toString())
                },
                onSuccess = {
                    progress.gone()
                    journalAdapter.setData = it.newData
                }
            )
        }
    }
    private fun setOnClickDetailJournal(data: Jounal){
        findNavController().navigate(JournalFragmentDirections.actionJournalFragmentToJournalDetailFragment(data))
    }
}