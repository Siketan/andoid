package com.wahidabd.siketan.presentation.journal

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.common.showSnackbarMessage
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.toStringTrim
import com.wahidabd.library.utils.exts.visible
import com.wahidabd.library.utils.exts.visibleIf
import com.wahidabd.siketan.data.farm.model.journal.JournalAddRequest
import com.wahidabd.siketan.databinding.FragmentJournalAddBinding
import com.wahidabd.siketan.presentation.report.viewmodel.AddressViewModel
import com.wahidabd.siketan.utils.PrefManager
import com.wahidabd.siketan.utils.datePicker
import com.wahidabd.siketan.utils.showCancelableDialog
import com.wahidabd.siketan.utils.showSuccessDialog
import com.wahidabd.siketan.utils.toDateApi
import com.wahidabd.siketan.utils.toDateString
import com.wahidabd.siketan.utils.validation.IValidator
import com.wahidabd.siketan.utils.validation.NotEmptyTilValidator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class JournalAddFragment : BaseFragment<FragmentJournalAddBinding>() {

    private val addressViewModel: AddressViewModel by viewModel()
    private val viewModel: JournalViewModel by viewModel()
    private val pref: PrefManager by inject()
    private val validators = mutableListOf<IValidator>()

    private var dateApi = emptyString()
    private var imageFile: File? = null

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentJournalAddBinding =
        FragmentJournalAddBinding.inflate(layoutInflater)

    override fun initUI() {
        with(binding) {
            setKecamatan()
            val user = pref.getUser()
            tilNip.setText(pref.getAttemptLoginPenyuluh().nip)
            tilName.setText(user.nama.toString())
        }

        validation()
    }

    override fun initAction() {
        with(binding) {
            imgBack.onClick { showCancelableDialog() }
            btnCancel.onClick { showCancelableDialog() }

            linearDate.onClick {
                datePicker { date ->
                    tvDate.text = date.toDateString()
                    dateApi = date.toDateApi()
                    debug { "Date -> ${date.toDateApi()}" }
                }
            }

            img.onClick {
                launcherPicker.launch(
                    ImagePickerConfig(
                        ImagePickerMode.SINGLE,
                        returnMode = ReturnMode.ALL
                    )
                )
            }
        }
    }


    override fun initProcess() {
        with(binding) {

            btnSave.onClick {
                if (validateAll() && imageFile != null && dateApi.isNotEmpty()) sendToObservable()
                else if (imageFile == null) showToast("Foto tanaman tidak boleh kosong!")
                else if (dateApi.isEmpty()) showToast("Tanggal tidak boleh kosong!")
                else return@onClick
            }
        }
    }

    override fun initObservers() {
        viewModel.add.observerLiveData(
            viewLifecycleOwner,
            onEmpty = {
                binding.progress.gone()
            },
            onLoading = {
                binding.progress.visible()
            },
            onFailure = { _, m ->
                binding.progress.gone()
                showSnackbarMessage(binding.root, m.toString())
            },
            onSuccess = {
                binding.progress.gone()
                showSnackbarMessage(binding.root, it.message)
                showSuccessDialog("JURNAL")
            }
        )
    }

    private fun sendToObservable() = with(binding) {
        val judul = tilJournal.edittext
        val desc = tilDesc.toStringTrim()

        val data = JournalAddRequest(
            nip = pref.getAttemptLoginPenyuluh().nip,
            judul = judul,
            tanggal = dateApi,
            uraian = desc,
            status = "Terbit",
            imageFile!!
        )
        viewModel.add(data)
    }

    private fun validation() = with(binding) {
        validators.add(NotEmptyTilValidator(tilJournal.til, "Judul jurnal tidak boleh kosong"))
        validators.add(NotEmptyTilValidator(tilDesc, "Deskripsi jurnal tidak boleh kosong"))
    }

    private fun validateAll(): Boolean {
        for (validator in validators) {
            if (!validator.isValid()) {
                val error = validator.message()
                showToast(error)
                return false
            }
        }
        return true
    }


    private val launcherPicker = registerImagePicker {
        with(binding) {
            it.forEach { image ->
                img.setImageURI(image.uri)
                imageFile = File(image.path)
            }
        }
    }

    private fun setKecamatan() = with(binding) {

        addressViewModel.kecamatan.observerLiveData(
            viewLifecycleOwner,
            onLoading = {},
            onEmpty = {},
            onFailure = { _, _ -> },
            onSuccess = {
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    it.data.map { f -> f.name })

                autoComplete.apply {
                    setAdapter(adapter)
                    setOnItemClickListener { _, _, i, _ ->
                        setKeluraha(it.data[i].id)
                    }
                }
            }
        )
    }

    private fun setKeluraha(id: Long) = with(binding) {
        addressViewModel.kelurahan(id)
        addressViewModel.kelurahan.observerLiveData(
            viewLifecycleOwner,
            onLoading = {
                kelurahan.gone()
                tvKelurahan.text = ""
            },
            onEmpty = {
                kelurahan.gone()
                tvKelurahan.text = ""
            },
            onFailure = { _, _ -> },
            onSuccess = {
                kelurahan.gone()
                tvKelurahan.text = ""
                kelurahan.visibleIf { it.data.isNotEmpty() }
                it.data.forEachIndexed { index, address ->
                    tvKelurahan.append("${index + 1}. ${address.name}\n")
                }
            }
        )
    }
}