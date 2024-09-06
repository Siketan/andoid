package id.go.ngawikab.siketan.presentation.report

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import id.go.ngawikab.siketan.databinding.FragmentReportPlantDataBinding
import id.go.ngawikab.siketan.presentation.report.viewmodel.ReportViewModel
import id.go.ngawikab.siketan.utils.common.SiketanBaseFragment
import id.go.ngawikab.siketan.utils.navigateUp
import go.ngawikab.siketan.presentation.report.adapter.ReportPlantDataAdapter
import id.go.ngawikab.siketan.R
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
                binding.tvTitle.text = getString(R.string.format_judul_laporan, it.daftarTani.komoditas.toString())
                reportPlantDataAdapter.setData = it.daftarTani.laporanTanams
            }
        )
    }
}