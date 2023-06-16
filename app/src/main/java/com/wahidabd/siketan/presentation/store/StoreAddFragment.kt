package com.wahidabd.siketan.presentation.store

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.siketan.databinding.FragmentStoreAddBinding

class StoreAddFragment : BaseFragment<FragmentStoreAddBinding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentStoreAddBinding = FragmentStoreAddBinding.inflate(layoutInflater)

    override fun initProcess() {}

    override fun initAction() {}

    override fun initUI() {}

    override fun initObservers() {}

}