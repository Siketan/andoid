package com.wahidabd.siketan.presentation.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.siketan.R
import com.wahidabd.siketan.databinding.FragmentReportPlantDataBinding
import com.wahidabd.siketan.presentation.report.viewmodel.ReportViewModel
import com.wahidabd.siketan.utils.common.SiketanBaseFragment
import com.wahidabd.siketan.utils.navigateUp
import go.ngawikab.siketan.presentation.report.adapter.ReportPlantDataAdapter
import org.koin.android.ext.android.inject

class ReportPlantDataFragment : SiketanBaseFragment<FragmentReportPlantDataBinding>() {

    private val args: ReportPlantDataFragmentArgs by navArgs()
    private val viewModel: ReportViewModel by inject()

    private val reportPlantDataAdapter by lazy {
        ReportPlantDataAdapter(requireContext())
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentReportPlantDataBinding =
        FragmentReportPlantDataBinding.inflate(layoutInflater)


    override fun initUI() {
        binding.rvTanaman.adapter = reportPlantDataAdapter
    }

    override fun initAction() {
        binding.imgBack.onClick { navigateUp() }
    }

    override fun initProcess() {
        viewModel.getLaporan(args.id)
    }

    override fun initObservers() {
        viewModel.getLaporan.observerLiveData(
            viewLifecycleOwner,
            onLoading = {
                showLoading()
            },
            onEmpty = {
                hideLoading()
            },
            onFailure = { _, message ->
                hideLoading()
                showToast(message.toString())
            },
            onSuccess = {
                hideLoading()
                reportPlantDataAdapter.setData = it.daftarTani.laporanTanams
            }
        )
    }

}