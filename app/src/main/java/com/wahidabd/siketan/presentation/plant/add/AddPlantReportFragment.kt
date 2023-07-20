package com.wahidabd.siketan.presentation.plant.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.siketan.databinding.FragmentAddPlantReportBinding
import com.wahidabd.siketan.utils.datePicker
import com.wahidabd.siketan.utils.toDateString

class AddPlantReportFragment : BaseFragment<FragmentAddPlantReportBinding>(){
    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentAddPlantReportBinding {
        return FragmentAddPlantReportBinding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {
        with(binding){
            imgBack.onClick { findNavController().navigateUp() }
            linearDate.onClick {
                datePicker { date ->
                    tvDate.text = date.toDateString()
                }
            }
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}

}