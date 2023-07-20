package com.wahidabd.siketan.presentation.plant.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.siketan.databinding.FragmentAddRealizationBinding
import com.wahidabd.siketan.utils.components.MyDialogFragment
import com.wahidabd.siketan.utils.datePicker
import com.wahidabd.siketan.utils.toDateString

class AddRealizationFragment : BaseFragment<FragmentAddRealizationBinding>(){

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentAddRealizationBinding =
        FragmentAddRealizationBinding.inflate(layoutInflater)


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