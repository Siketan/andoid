package com.wahidabd.siketan.presentation.auth.authentication

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.siketan.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(){


    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {}

    override fun initObservers() {}

}