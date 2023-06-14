package com.wahidabd.siketan.presentation.announcement

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.siketan.databinding.FragmentAnnouncementBinding

class AnnouncementFragment : BaseFragment<FragmentAnnouncementBinding>() {


    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentAnnouncementBinding {
        return FragmentAnnouncementBinding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {}

    override fun initObservers() {}

}