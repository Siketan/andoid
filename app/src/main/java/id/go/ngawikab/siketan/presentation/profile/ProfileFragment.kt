package id.go.ngawikab.siketan.presentation.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.showDefaultState
import com.wahidabd.library.utils.extensions.showEmptyState
import com.wahidabd.library.utils.extensions.showLoadingState
import com.wahidabd.library.utils.exts.clear
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.setImageUrl
import com.wahidabd.library.utils.exts.toStringTrim
import com.wahidabd.library.utils.exts.visibleIf
import id.go.ngawikab.siketan.data.auth.model.user.UserEditeRequest
import id.go.ngawikab.siketan.databinding.FragmentProfileBinding
import id.go.ngawikab.siketan.domain.auth.model.User
import id.go.ngawikab.siketan.presentation.report.viewmodel.AddressViewModel
import id.go.ngawikab.siketan.utils.PrefManager
import id.go.ngawikab.siketan.utils.UserRole
import id.go.ngawikab.siketan.utils.navigateUp
import id.go.ngawikab.siketan.utils.validation.IValidator
import id.go.ngawikab.siketan.utils.validation.NotEmptyTilValidator
import org.koin.android.ext.android.inject
import java.io.File


class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val addressViewModel: AddressViewModel by inject()
    private val viewModel: ProfileViewModel by inject()
    private val pref: PrefManager by inject()

    private var imageFile: File? = null
    private var kecValue: String? = null
    private var desaValue: String? = null
    private var data = UserEditeRequest()
    private val validators = mutableListOf<IValidator>()

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentProfileBinding = FragmentProfileBinding.inflate(layoutInflater)


    override fun initUI() {
        with(binding) {
            tilNik.disable
            tilName.disable

            val user = pref.getUser()
            btnSave.visibleIf { user.role == UserRole.PETANI.role }
            spacer.visibleIf { user.role == UserRole.PETANI.role }
        }
    }

    override fun initAction() {
        with(binding) {
            imgProfile.onClick {
                launcherImage.launch(
                    ImagePickerConfig(
                        ImagePickerMode.SINGLE,
                        returnMode = ReturnMode.ALL
                    )
                )
            }

            btnCancel.onClick { navigateUp() }
            btnSave.onClick { if (validateAll()) sendToObserver() }
        }
    }

    override fun initProcess() {
        viewModel.user(pref.getUser().id ?: 0)
        setKecamatan()
        validate()
    }

    override fun initObservers() {
        with(binding) {
            viewModel.user.observerLiveData(
                viewLifecycleOwner,
                onLoading = {
                    msv.showLoadingState()
                },
                onEmpty = {},
                onFailure = { _, mess ->
                    msv.showEmptyState()
                    showToast(mess.toString())
                },
                onSuccess = {
                    msv.showDefaultState()
                    val role = pref.getUser().role
                    val res = it.detailTani
                    tilNik.setText( res.nik.toString())
                    tilName.setText(res.nama.toString())
                    tilNoWhatsapp.setText(res.NoWa ?: "")
                    tilAddress.setText(res.alamat ?: "")
                    kecamatan.setText(res.kecamatan, false)
                    desa.setText(res.desa, false)
                    btnSave.gone()
                    btnCancel.gone()
                    if (res.foto?.isNotEmpty() == true) {
                        imgProfile.setImageUrl(requireContext(), res.foto.toString(), true)
                    }

                },
            )

            viewModel.edit.observerLiveData(
                viewLifecycleOwner,
                onLoading = {},
                onEmpty = {},
                onFailure = { _, _ -> },
                onSuccess = {
                    showToast(it.message)

                    pref.setUser(
                        User(
                            id = data.id,
                            nik = data.NIK,
                            nama = data.nama,
                            NoWa = data.NoWa,
                            alamat = data.alamat,
                            kecamatan = data.kecamatan,
                            desa = data.desa,
                            role = "petani"
                        )
                    )
                }
            )
        }
    }

    private fun sendToObserver() = with(binding) {
        val name = tilName.edittext
        val nowa = tilNoWhatsapp.edittext
        val nik = tilNik.edittext
        val kecamatan = tilKecamatan.toStringTrim()
        val desa = tilDesa.toStringTrim()
        val address = tilAddress.edittext

        data = UserEditeRequest(
            id = pref.getUser().id,
            NIK = nik,
            nama = name,
            alamat = address,
            kecamatan = kecamatan,
            desa = desa,
            NoWa = nowa,
            foto = imageFile
        )

        viewModel.edit(data)
    }

    private val launcherImage = registerImagePicker {
        it.forEach { image ->
            binding.imgProfile.setImageURI(image.uri)
            imageFile = File(image.path)
        }
    }

    private fun setKecamatan() = with(binding) {
        addressViewModel.kecamatan.observerLiveData(
            viewLifecycleOwner,
            onLoading = {},
            onEmpty = {},
            onFailure = { _, _ -> },
            onSuccess = {
                val res = it.data
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    res.map { f -> f.name }
                )
                kecamatan.apply {
                    setAdapter(adapter)
                    setOnItemClickListener { _, _, i, _ ->
                        tilDesa.clear()
                        desaValue = null
                        kecValue = res[i].name
                        setDesa(res[i].id)
                    }
                }
            }
        )
    }

    private fun setDesa(id: Long) = with(binding) {
        addressViewModel.kelurahan(id)
        addressViewModel.kelurahan.observerLiveData(
            viewLifecycleOwner,
            onLoading = {},
            onEmpty = {},
            onFailure = { _, _ -> },
            onSuccess = {
                val res = it.data
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    res.map { f -> f.name }
                )
                desa.apply {
                    setAdapter(adapter)
                    setOnItemClickListener { _, _, i, _ ->
                        desaValue = res[i].name
                    }
                }
            }
        )
    }

    private fun validate() = with(binding) {
        validators.add(NotEmptyTilValidator(tilNik.til, "Nik Tidak Boleh Kosong"))
        validators.add(NotEmptyTilValidator(tilName.til, "Nama Tidak Boleh Kosong"))
        validators.add(NotEmptyTilValidator(tilAddress.til, "Nik Tidak Boleh Kosong"))
        validators.add(NotEmptyTilValidator(tilNoWhatsapp.til, "Nik Tidak Boleh Kosong"))
        validators.add(NotEmptyTilValidator(tilKecamatan, "Kecamatan tidak boleh kosong"))
        validators.add(NotEmptyTilValidator(tilDesa, "Kecamatan tidak boleh kosong"))
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