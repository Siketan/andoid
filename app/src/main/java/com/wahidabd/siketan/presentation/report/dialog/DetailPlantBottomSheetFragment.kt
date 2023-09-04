package com.wahidabd.siketan.presentation.report.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseBottomSheetDialogFragment
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.siketan.R
import com.wahidabd.siketan.data.farm.model.farm.response.TanamanPetaniDataResponse
import com.wahidabd.siketan.databinding.FragmentDetailPlantBottomSheetBinding

class DetailPlantBottomSheetFragment : BaseBottomSheetDialogFragment<FragmentDetailPlantBottomSheetBinding>() {


    private var data = TanamanPetaniDataResponse()
    private var onButtonClicked: (Int) -> Unit = {}
    private var onDetailClicked: (Int) -> Unit = {}

    companion object {
        private const val KEY_DATA = "key_data"
        fun newInstance(
            data: TanamanPetaniDataResponse,
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
            tvJenisPanen.text = " : ${(data.jenisPanen ?: "-")}"
            tvMusimTanam.text = " : ${(data.musimTanam ?: "-")}"
            tvTanggalTanam.text = " : ${(data.tanggalTanam ?: "-")}"
            tvPrakiraanPanen.text = " : ${(data.perkiraanPanen ?: "-")}"
            tvStatusLahan.text = " : ${(data.statusLahan ?: "-")}"
            tvLuasLahan.text = " : ${(data.luasLahan ?: "-")}"
            tvPrakiraanHasilPanen.text = " : ${(data.perkiraanHasilPanen ?: "-")}"
            tvRealisasiHasilPanen.text = " : ${(data.realisasiHasilPanen ?: "-")}"
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