package id.go.ngawikab.siketan.presentation.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.showDefaultState
import com.wahidabd.library.utils.extensions.showLoadingState
import com.wahidabd.library.utils.exts.clear
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.setImageUrl
import com.wahidabd.library.utils.exts.toStringTrim
import com.wahidabd.library.utils.exts.visibleIf
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.data.auth.model.user.UserEditeRequest
import id.go.ngawikab.siketan.databinding.FragmentProfileBinding
import id.go.ngawikab.siketan.domain.address.model.Address
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
            if (user.role == UserRole.PENYULUH.role) {
                tilName.setHint(getString(R.string.hint_penyuluh))
            }
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
            checkBoxChangePassword.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) tilPassword.visibility = LinearLayout.VISIBLE
                else tilPassword.visibility = LinearLayout.GONE
            }
            btnCancel.onClick { navigateUp() }
            btnSave.onClick { if (validateAll()) sendToObserver() }
        }
    }

    override fun initProcess() {
        tampilkanData()
        setKecamatan()
        viewModel.kecValue?.let { setDesa(it.toLong()) }
        validate()
    }

    override fun initObservers() {
        with(binding) {
            viewModel.edit.observerLiveData(
                viewLifecycleOwner,
                onLoading = {
                    msv.showLoadingState()
                },
                onEmpty = {},
                onFailure = { _, _ ->
                    msv.showDefaultState()
                    showToast("Update data gagal")
                },
                onSuccess = {
                    msv.showDefaultState()
                    showToast(it.message)
                    pref.setUser(
                        User(
                            id = pref.getUser().id,
                            nik = data.nik,
                            nama = data.nama,
                            noTelp = data.whatsapp,
                            alamat = data.alamat,
                            role = pref.getUser().role,
                        )
                    )
                    pref.setUserKecamatanDesa(
                        User(
                            kecamatanData = Address(
                                id = data.kecamatanId?.toLong() ?: 0,
                                nama = data.kecamatan ?: ""
                            ),
                            desaData = Address(
                                id = data.desaId?.toLong() ?: 0,
                                nama = data.desa ?: ""
                            )
                        )
                    )
                    val password = when {
                        !data.passwordBaru.isNullOrEmpty() -> data.passwordBaru
                        else -> data.password
                    }
                    pref.setPassword(password)
                }
            )
        }
    }

    private fun tampilkanData() = with(binding){
        val res = pref.getUser()
        val phoneNumber = when {
            !res.noTelp.isNullOrEmpty() -> res.noTelp
            !res.NoWa.isNullOrEmpty() -> res.NoWa
            else -> ""
        }
        tilNik.setText(res.nik.toString())
        tilName.setText(res.nama.toString())
        tilNoWhatsapp.setText(phoneNumber)
        tilAddress.setText(res.alamat ?: "")
        kecamatan.setText(res.kecamatanData?.nama, false)
        desa.setText(res.desaData?.nama, false)
        viewModel.kecValue = res.kecamatanData?.id?.toInt()
        viewModel.desaValue = res.desaData?.id?.toInt()
        if (res.foto?.isNotEmpty() == true) {
            imgProfile.setImageUrl(requireContext(), res.foto.toString(), true)
        }
    }

    private fun sendToObserver() = with(binding) {
        val name = tilName.getText()
        val nowa = tilNoWhatsapp.getText()
        val nik = tilNik.getText()
        val kecamatan = tilKecamatan.toStringTrim()
        val desa = tilDesa.toStringTrim()
        val address = tilAddress.getText()
        val password = pref.getAttemptLogin().password.trim()
        val passwordBaru = tilPassword.getText().trim()
        data = UserEditeRequest(
            nik = nik,
            nama = name,
            alamat = address,
            kecamatan = kecamatan,
            kecamatanId = viewModel.kecValue,
            desa = desa,
            desaId = viewModel.desaValue,
            whatsapp = nowa,
            foto = imageFile,
            password = password,
            passwordBaru = passwordBaru
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
                    res.map { f -> f.nama }
                )
                kecamatan.apply {
                    setAdapter(adapter)
                    setOnItemClickListener { _, _, i, _ ->
                        tilDesa.clear()
                        viewModel.kecValue = res[i].id.toInt()
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
                    res.map { f -> f.nama }
                )
                desa.apply {
                    setAdapter(adapter)
                    setOnItemClickListener { _, _, i, _ ->
                        viewModel.desaValue = res[i].id.toInt()
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