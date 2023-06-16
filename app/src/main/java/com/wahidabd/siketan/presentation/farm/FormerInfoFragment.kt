package com.wahidabd.siketan.presentation.farm

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.siketan.databinding.FragmentFormerInfoBinding
import com.wahidabd.siketan.presentation.announcement.AnnouncementAdapter

class FormerInfoFragment : BaseFragment<FragmentFormerInfoBinding>() {

    private val mAdapter by lazy {
        AnnouncementAdapter(requireContext())
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentFormerInfoBinding =
        FragmentFormerInfoBinding.inflate(layoutInflater)

    override fun initUI() {
        with(binding) {
            rvStore.adapter = mAdapter
        }
    }

    override fun initAction() {}

    override fun initProcess() {}

    override fun initObservers() {}

}