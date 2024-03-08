package id.go.ngawikab.siketan.presentation.report.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseBottomSheetDialogFragment
import com.wahidabd.library.utils.exts.onClick
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.data.farm.model.farm.response.PlantFarmerData
import id.go.ngawikab.siketan.databinding.FragmentDetailPlantBottomSheetBinding

class DetailPlantBottomSheetFragment : BaseBottomSheetDialogFragment<FragmentDetailPlantBottomSheetBinding>() {

    private var data = PlantFarmerData()
    private var onButtonClicked: (Int) -> Unit = {}
    private var onDetailClicked: (Int) -> Unit = {}

    companion object {
        private const val KEY_DATA = "key_data"
        fun newInstance(
            data: PlantFarmerData,
            onButtonClicked: (Int) -> Unit,
            onDetailClicked: (Int) -> Unit
        ): DetailPlantBottomSheetFragment {
            return DetailPlantBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_DATA, data)
                }
                this.onButtonClicked = onButtonClicked
                this.onDetailClicked = onDetailClicked
            }
        }
    }

    override val bottomSheetTheme: Int = R.style.BottomDialogTheme
    override val tagName: String = DetailPlantBottomSheetFragment::class.java.name

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentDetailPlantBottomSheetBinding =
        FragmentDetailPlantBottomSheetBinding.inflate(layoutInflater)

    override fun initUI() {
        data = arguments?.getParcelable(KEY_DATA)!!

        with(binding) {
            tvTitle.text = data.komoditas
            tvKategori.text = " : ${data.kategori}"
            tvJenisTanaman.text = " : ${(data.jenis ?: "-")}"
            tvJenisPanen.text = " : ${(data.jenis ?: "-")}"
            tvMusimTanam.text = " : ${(data.periodeMusimTanam ?: "-")}"
            tvTanggalTanam.text = " : ${(data.periodeBulanTanam ?: "-")}"
            tvPrakiraanPanen.text = " : ${(data.prakiraanBulanPanen ?: "-")}"
            tvStatusLahan.text = " : ${(data.statusKepemilikanLahan ?: "-")}"
            tvLuasLahan.text = " : ${(data.luasLahan ?: "-")}"
            tvPrakiraanHasilPanen.text = " : ${(data.prakiraanProduksiPanen ?: "-")}"
            tvRealisasiHasilPanen.text = " : ${(data.prakiraanProduksiPanen ?: "-")}"
        }
    }

    override fun initAction() {
        binding.imgClose.onClick { dismiss() }
        binding.btnReport.onClick {
            onButtonClicked.invoke(data.id!!)
            dismiss()
        }
        binding.btnDetail.onClick {
            onDetailClicked.invoke(data.id!!)
            dismiss()
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}

}