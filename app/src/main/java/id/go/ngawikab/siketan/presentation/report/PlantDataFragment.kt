package id.go.ngawikab.siketan.presentation.report

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import id.go.ngawikab.siketan.databinding.FragmentPlantDataBinding
import go.ngawikab.siketan.presentation.report.adapter.PlantDataFormerAdapter
import id.go.ngawikab.siketan.data.farm.model.farm.response.PlantFarmerData
import id.go.ngawikab.siketan.presentation.report.dialog.DetailPlantBottomSheetFragment
import id.go.ngawikab.siketan.presentation.report.viewmodel.ReportViewModel
import id.go.ngawikab.siketan.utils.PrefManager
import id.go.ngawikab.siketan.utils.common.SiketanBaseFragment
import id.go.ngawikab.siketan.utils.navigate
import id.go.ngawikab.siketan.utils.navigateUp
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
        viewModel.getTanaman(pref.getUser().id!!,1,10)
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
                    plantAdapter.setData = it.data
                }
            )
        }
    }

    private fun showDetail(data: PlantFarmerData) {
        DetailPlantBottomSheetFragment.newInstance(
            data,
            onButtonClicked = {
                navigate(
                    PlantDataFragmentDirections.actionPlantDataFragmentToAddPlantReportFragment(it)
                )
            },
            onDetailClicked = {
                navigate(
                    PlantDataFragmentDirections.actionPlantDataFragmentToReportPlantDataFragment(it)
                )
            }
        ).show(parentFragmentManager, PlantDataFragment::class.java.name)
    }

}