package com.wahidabd.siketan.presentation.report.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.toStringTrim
import com.wahidabd.siketan.data.farm.model.farm.request.LaporanTanamanRequest
import com.wahidabd.siketan.databinding.FragmentAddPlantReportBinding
import com.wahidabd.siketan.presentation.report.viewmodel.ReportViewModel
import com.wahidabd.siketan.utils.common.SiketanBaseFragment
import com.wahidabd.siketan.utils.datePicker
import com.wahidabd.siketan.utils.getCurrentDate
import com.wahidabd.siketan.utils.showCancelableDialog
import com.wahidabd.siketan.utils.showSuccessDialog
import com.wahidabd.siketan.utils.toDateString
import org.koin.android.ext.android.inject
import java.io.File

class AddPlantReportFragment : SiketanBaseFragment<FragmentAddPlantReportBinding>(){
    private val args: AddPlantReportFragmentArgs by navArgs()
    private var imageFile: File? = null
    private val viewModel: ReportViewModel by inject()

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentAddPlantReportBinding {
        return FragmentAddPlantReportBinding.inflate(layoutInflater)
    }

    override fun initUI() {

    }

    override fun initAction() {
        with(binding) {
            imgBack.onClick { findNavController().navigateUp() }
            btnCancel.onClick { showCancelableDialog() }
            edtImage.onClick {
                launcherPicker.launch(
                    ImagePickerConfig(
                        ImagePickerMode.SINGLE,
                        returnMode = ReturnMode.GALLERY_ONLY
                    )
                )
            }

            btnSubmit.onClick {
                val kondisi = tilKondisiTanaman.edittext
                val deskripsi = tilDeskripsi.toStringTrim()

                val data = LaporanTanamanRequest(
                    args.id,
                    getCurrentDate(),
                    kondisi,
                    deskripsi,
                    imageFile
                )

                viewModel.addLaporan(data)
            }
        }
    }

    override fun initProcess() {}

    override fun initObservers() {
        viewModel.addLaporan.observerLiveData(
            viewLifecycleOwner,
            onLoading = {
                showLoading()
            },
            onEmpty = {
                hideLoading()
            },
            onFailure = { _, m ->
                hideLoading()
                showToast(m.toString())
            },
            onSuccess = {
                hideLoading()
                showSuccessDialog("LAPORAN TANAMAN")
            }
        )
    }

    private val launcherPicker = registerImagePicker {
        with(binding) {
            it.forEach { image ->
                edtImage.setText(image.name)
                imageFile = File(image.path)
            }
        }
    }

}