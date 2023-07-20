package com.wahidabd.siketan.presentation.plant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.siketan.databinding.FragmentDataFormerBinding
import com.wahidabd.siketan.utils.navigate

class DataFormerFragment : BaseFragment<FragmentDataFormerBinding>() {

    private var reveal: Boolean = true

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentDataFormerBinding =
        FragmentDataFormerBinding.inflate(layoutInflater)

    override fun initUI() {}

    override fun initAction() {
        with(binding) {
            imgBack.onClick { findNavController().navigateUp() }
            fabAdd.onClick {
                fabAdd.isExpanded = reveal
                reveal = !reveal
            }
            linearTop.onClick { navigate(DataFormerFragmentDirections.actionDataFormerFragmentToAddRealizationFragment()) }
            linearBottom.onClick { navigate(DataFormerFragmentDirections.actionDataFormerFragmentToAddPlantReportFragment()) }
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}

}