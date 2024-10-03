package id.go.ngawikab.siketan.presentation.journal

import android.R.layout.simple_list_item_1
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
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.toStringTrim
import com.wahidabd.library.utils.exts.visible
import com.wahidabd.library.utils.exts.visibleIf
import id.go.ngawikab.siketan.data.farm.model.journal.PresensiRequest
import id.go.ngawikab.siketan.databinding.FragmentPresensiBinding
import id.go.ngawikab.siketan.presentation.report.viewmodel.AddressViewModel
import id.go.ngawikab.siketan.utils.PrefManager
import id.go.ngawikab.siketan.utils.datePicker
import id.go.ngawikab.siketan.utils.showCancelableDialog
import id.go.ngawikab.siketan.utils.showSuccessDialog
import id.go.ngawikab.siketan.utils.toDateApi
import id.go.ngawikab.siketan.utils.toDateString
import id.go.ngawikab.siketan.utils.validation.IValidator
import id.go.ngawikab.siketan.utils.validation.NotEmptyTilValidator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class PresensiFragment : BaseFragment<FragmentPresensiBinding>() {

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
    ): FragmentPresensiBinding {
        return FragmentPresensiBinding.inflate(layoutInflater)
    }

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
            linearDate.onClick {
                datePicker { date ->
                    tvDate.text = date.toDateString()
                    dateApi = date.toDateApi()
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
            btnCancel.onClick { showCancelableDialog() }
            btnSave.onClick {
                if (validateAll() && imageFile != null && dateApi.isNotEmpty()) sendToObservable()
                else if (imageFile == null) showToast("Foto tanaman tidak boleh kosong!")
                else if (dateApi.isEmpty()) showToast("Tanggal tidak boleh kosong!")
                else return@onClick
            }
        }
    }

    override fun initObservers() {
        viewModel.presensi.observerLiveData(
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
                showSuccessDialog("PRESENSI")
            }
        )
    }

    private fun sendToObservable() {
        with(binding) {
            val judul = tilJournal.edittext
            val desc = tilDesc.toStringTrim()

            val data = PresensiRequest(
                nip = pref.getAttemptLoginPenyuluh().nip,
                title = judul,
                date = dateApi,
                desc = desc,
                file = imageFile!!
            )

            viewModel.presensi(data)
        }
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
                    simple_list_item_1,
                    it.data.map { f -> f.nama })

                autoComplete.apply {
                    setAdapter(adapter)
                    setOnItemClickListener { _, _, i, _ ->
                        setKelurahan(it.data[i].id)
                    }
                }
            }
        )
    }

    private fun setKelurahan(id: Long) = with(binding) {
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
                    tvKelurahan.append("${index + 1}. ${address.nama}\n")
                }
            }
        )
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
}