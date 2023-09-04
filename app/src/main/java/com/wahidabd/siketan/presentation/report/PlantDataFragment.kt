package com.wahidabd.siketan.presentation.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.siketan.R
import com.wahidabd.siketan.data.farm.model.farm.response.TanamanPetaniDataResponse
import com.wahidabd.siketan.databinding.FragmentPlantDataBinding
import com.wahidabd.siketan.presentation.report.dialog.DetailPlantBottomSheetFragment
import com.wahidabd.siketan.presentation.report.viewmodel.ReportViewModel
import com.wahidabd.siketan.utils.PrefManager
import com.wahidabd.siketan.utils.common.SiketanBaseFragment
import com.wahidabd.siketan.utils.navigate
import com.wahidabd.siketan.utils.navigateUp
import go.ngawikab.siketan.presentation.report.adapter.PlantDataFormerAdapter
import org.koin.android.ext.android.inject

class PlantDataFragment : SiketanBaseFragment<FragmentPlantDataBinding>() {

    private val pref: PrefManager by inject()
    private val viewModel: ReportViewModel by inject()

    private val plantAdapter by lazy {
        PlantDataFormerAdapter(
            requireContext(),
            onItemClick = { showDetail(it) },
            onMoreClick = {}
        )
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentPlantDataBinding = FragmentPlantDataBinding.inflate(layoutInflater)


    override fun initUI() {
        binding.rvTanaman.adapter = plantAdapter
    }

    override fun initAction() {
        with(binding) {
            imgBack.onClick { navigateUp() }
        }
    }

    override fun initProcess() {
        viewModel.getTanaman(pref.getUser().id!!)
    }

    override fun initObservers() {
        with(binding) {
            viewModel.getTanaman.observerLiveData(
                viewLifecycleOwner,
                onLoading = {
                    showLoading()
                },
                onEmpty = {
                    hideLoading()
                    showToast("Anda belum memiliki tanaman!")
                },
                onFailure = { _, message ->
                    hideLoading()
                    showToast(message.toString())
                },
                onSuccess = {
                    hideLoading()
                    plantAdapter.setData = it.tani.tanamanPetanis
                }
            )
        }
    }

    private fun showDetail(data: TanamanPetaniDataResponse) {
        DetailPlantBottomSheetFragment.newInstance(
            data,
            onButtonClicked = {
                navigate(PlantDataFragmentDirections.actionPlantDataFragmentToAddPlantReportFragment(it))
            },
            onDetailClicked = {
                navigate(PlantDataFragmentDirections.actionPlantDataFragmentToReportPlantDataFragment(it))
            }
        ).show(parentFragmentManager, PlantDataFragment::class.java.name)
    }

}