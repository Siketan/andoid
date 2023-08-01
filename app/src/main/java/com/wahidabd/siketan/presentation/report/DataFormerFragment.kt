package com.wahidabd.siketan.presentation.report

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.visibleIf
import com.wahidabd.siketan.databinding.FragmentDataFormerBinding
import com.wahidabd.siketan.utils.PrefManager
import com.wahidabd.siketan.utils.UserRole
import com.wahidabd.siketan.utils.navigate
import org.koin.android.ext.android.inject

class DataFormerFragment : BaseFragment<FragmentDataFormerBinding>() {

    private val pref: PrefManager by inject()
    private var reveal: Boolean = true

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentDataFormerBinding =
        FragmentDataFormerBinding.inflate(layoutInflater)

    override fun initUI() {
        with(binding){
            fabAdd.visibleIf { pref.getUser().role == UserRole.PETANI.role }
        }
    }

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