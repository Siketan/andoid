package com.wahidabd.siketan.presentation.plant

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.siketan.databinding.FragmentAddPlantReportBinding

class AddPlantReportFragment : BaseFragment<FragmentAddPlantReportBinding>(){
    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentAddPlantReportBinding {
        return FragmentAddPlantReportBinding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {}

    override fun initObservers() {}

}