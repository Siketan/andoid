package id.go.ngawikab.siketan.presentation.report

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import id.go.ngawikab.siketan.databinding.FragmentPlantDataBinding
import go.ngawikab.siketan.presentation.report.adapter.PlantDataFormerAdapter
import id.go.ngawikab.siketan.data.farm.model.farm.response.PlantFarmerData
import id.go.ngawikab.siketan.presentation.report.adapter.PlantDataPagingAdapter
import id.go.ngawikab.siketan.presentation.report.dialog.DetailPlantBottomSheetFragment
import id.go.ngawikab.siketan.presentation.report.viewmodel.ReportViewModel
import id.go.ngawikab.siketan.presentation.store.StoreLoadStateAdapter
import id.go.ngawikab.siketan.presentation.store.StorePagingAdapter
import id.go.ngawikab.siketan.utils.PrefManager
import id.go.ngawikab.siketan.utils.common.SiketanBaseFragment
import id.go.ngawikab.siketan.utils.navigate
import id.go.ngawikab.siketan.utils.navigateUp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class PlantDataFragment : SiketanBaseFragment<FragmentPlantDataBinding>() {

    private val pref: PrefManager by inject()
    private val viewModel: ReportViewModel by inject()

    private lateinit var plantAdapter: PlantDataPagingAdapter


    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentPlantDataBinding = FragmentPlantDataBinding.inflate(layoutInflater)


    override fun initUI() {
         plantAdapter =   PlantDataPagingAdapter(
            requireContext(),
            onItemClick = { showDetail(it) },
            onMoreClick = {}
        )
        binding.rvTanaman.apply {
            adapter = plantAdapter.withLoadStateHeaderAndFooter(
                header = StoreLoadStateAdapter{plantAdapter.retry()},
                footer = StoreLoadStateAdapter{plantAdapter.retry()}
            )
            itemAnimator = DefaultItemAnimator()
        }

        subscribe()
        loadState()
    }

    private fun subscribe(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tanaman(pref.getUser().id!!).collectLatest{
                    plantAdapter.submitData(it)
                }
            }
        }
    }

    private fun loadState(){
        plantAdapter.addLoadStateListener { loadState ->
            binding.apply {
                rvTanaman.isVisible = loadState.source.refresh is LoadState.NotLoading
                progress.isVisible = loadState.source.refresh is LoadState.Loading

                rvTanaman.isVisible = !(loadState.source.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached && plantAdapter.itemCount < 1)
            }
        }
    }

    override fun initAction() {
        with(binding) {
            imgBack.onClick { navigateUp() }
        }
    }

    override fun initProcess() {
    }

    override fun initObservers() {
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