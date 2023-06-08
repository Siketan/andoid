package com.wahidabd.siketan.presentation.auth.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.siketan.databinding.FragmentOnBoarding1Binding

class OnBoarding1Fragment : BaseFragment<FragmentOnBoarding1Binding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentOnBoarding1Binding {
        return FragmentOnBoarding1Binding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {}

    override fun initObservers() {}

}