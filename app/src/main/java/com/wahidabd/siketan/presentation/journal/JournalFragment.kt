package com.wahidabd.siketan.presentation.journal

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
import com.wahidabd.siketan.databinding.FragmentJournalBinding
import com.wahidabd.siketan.presentation.journal.adapter.JournalAdapter
import com.wahidabd.siketan.utils.PrefManager
import com.wahidabd.siketan.utils.UserRole
import com.wahidabd.siketan.utils.navigate
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class JournalFragment : BaseFragment<FragmentJournalBinding>() {

    private val viewModel: JournalViewModel by viewModel()
    private val journalAdapter by lazy {
        JournalAdapter(requireContext())
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

}