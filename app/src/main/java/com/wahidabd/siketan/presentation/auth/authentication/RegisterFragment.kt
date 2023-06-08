package com.wahidabd.siketan.presentation.auth.authentication

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.siketan.databinding.FragmentRegisterBinding


class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentRegisterBinding {
        return FragmentRegisterBinding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {}

    override fun initObservers() {}

}