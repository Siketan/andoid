package com.wahidabd.siketan.presentation.journal

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.siketan.databinding.FragmentJournalBinding
import com.wahidabd.siketan.utils.navigate

class JournalFragment : BaseFragment<FragmentJournalBinding>() {

    private var reveal: Boolean = true

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentJournalBinding =
        FragmentJournalBinding.inflate(layoutInflater)

    override fun initUI() {}

    override fun initAction() {
        with(binding){
            fabAdd.onClick {
                fabAdd.isExpanded = reveal
                reveal = !reveal
            }

            imgEdit.onClick {
                navigate(JournalFragmentDirections.actionJournalFragmentToJournalAddFragment())
            }
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}

}