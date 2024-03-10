package id.go.ngawikab.siketan.presentation.report

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.showDefaultState
import com.wahidabd.library.utils.extensions.showLoadingState
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.visibleIf
import id.go.ngawikab.siketan.data.farm.model.farm.response.ChartResponse
import id.go.ngawikab.siketan.databinding.FragmentDataFormerBinding
import id.go.ngawikab.siketan.domain.farm.model.request.Chartparam
import id.go.ngawikab.siketan.domain.farm.model.response.ChartModel
import id.go.ngawikab.siketan.presentation.report.viewmodel.ReportViewModel
import id.go.ngawikab.siketan.utils.ChartKomuditasQuery
import id.go.ngawikab.siketan.utils.ChartTypeQuery
import id.go.ngawikab.siketan.utils.PrefManager
import id.go.ngawikab.siketan.utils.UserRole
import id.go.ngawikab.siketan.utils.common.Dummy
import id.go.ngawikab.siketan.utils.navigate
import id.go.ngawikab.siketan.utils.randomColor
import org.koin.android.ext.android.inject

class DataFormerFragment : BaseFragment<FragmentDataFormerBinding>() {

    private val pref: PrefManager by inject()
    private var reveal: Boolean = true

    private var dataQuery = Chartparam()
    private val viewModel: ReportViewModel by inject()

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentDataFormerBinding =
        FragmentDataFormerBinding.inflate(layoutInflater)

    override fun initUI() {
        with(binding) {
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
            linearBottom.onClick { navigate(DataFormerFragmentDirections.actionDataFormerFragmentToPlantDataFragment()) }
        }
    }

    override fun initProcess() {
        val userId = pref.getUser().id
        val query = Chartparam(
            userId,
        )
        viewModel.getChart(query)
    }

    override fun initObservers() {
        viewModel.chart.observerLiveData(
            viewLifecycleOwner,
            onEmpty = {},
            onLoading = {
                binding.msv.showLoadingState()
            },
            onFailure = { _, message ->
                binding.msv.showDefaultState()
                barChart(Dummy.generateChartDummy())
                pieChart(Dummy.generateChartDummy())
            },
            onSuccess = {
                binding.msv.showDefaultState()
                barChart(it)
                pieChart(it)
            }
        )
    }

    private fun barChart(data: ChartResponse) = with(binding) {
        with(barChart) {
            setDrawBarShadow(false)
            setDrawValueAboveBar(true)
            description.isEnabled = false

            // hide the y and x axis only right
            axisRight.isEnabled = false
            xAxis.isEnabled = false

            setMaxVisibleValueCount(60)
            setPinchZoom(true)
            setDrawGridBackground(false)
            legend.isWordWrapEnabled = true
            animate()
            invalidate()
        }

        with(barChart.legend) {
            verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
            horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
            orientation = Legend.LegendOrientation.HORIZONTAL
            setDrawInside(false)
            form = Legend.LegendForm.SQUARE
            formSize = 9f
            textSize = 11f
            xEntrySpace = 4f
        }
        barChart.legend.isEnabled = true

        val list = ArrayList<BarDataSet>()
        data.data?.forEachIndexed { index, model ->
            val value = BarDataSet(
                listOf(model?.count?.let { BarEntry(index.toFloat(), it.toFloat()) }),
                model?.komoditas
            )

            value.color = requireContext().randomColor()
            list.add(value)
        }

        val dataset = BarData(list as List<IBarDataSet>)
        barChart.animate()
        barChart.invalidate()
        barChart.data = dataset
    }

    private fun pieChart(data: ChartResponse) = with(binding) {
        val entries = data.data?.map {
            it?.count?.toFloat()
                ?.let { it1 -> PieEntry(it1, "${it.komoditas} ${it.periodeMusimTanam}") }
        }

        // Create the dataset and customize it
        val pieDataSet = PieDataSet(entries, "")
        pieDataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        pieDataSet.sliceSpace = 1f
        pieDataSet.selectionShift = 3f

        // Add data to the PieChart
        val pieData = PieData(pieDataSet)
        pieData.setValueFormatter(PercentFormatter())
        pieData.setValueTextColor(Color.WHITE)
        pieData.setValueTextSize(12f)
        pieChart.data = pieData

        // legend
        pieChart.legend.apply {
            verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
            horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
            orientation = Legend.LegendOrientation.HORIZONTAL
            setDrawInside(false)
            form = Legend.LegendForm.SQUARE
            formSize = 9f
            textSize = 11f
            isWordWrapEnabled = true
        }

        // Customize the PieChart
        pieChart.setUsePercentValues(true)
        pieChart.description.isEnabled = false
        pieChart.isDrawHoleEnabled = false
        pieChart.transparentCircleRadius = 35f
        pieChart.setDrawEntryLabels(false)
        pieChart.animateY(1000)
        pieChart.invalidate()
    }
}