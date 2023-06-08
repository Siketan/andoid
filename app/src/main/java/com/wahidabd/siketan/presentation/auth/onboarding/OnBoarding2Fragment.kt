package com.wahidabd.siketan.presentation.auth.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.siketan.databinding.FragmentOnBoarding2Binding

class OnBoarding2Fragment : BaseFragment<FragmentOnBoarding2Binding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentOnBoarding2Binding {
        return FragmentOnBoarding2Binding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {}

    override fun initObservers() {}

}