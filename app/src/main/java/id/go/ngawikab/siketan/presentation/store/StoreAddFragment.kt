package id.go.ngawikab.siketan.presentation.store

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
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.databinding.FragmentStoreAddBinding
import id.go.ngawikab.siketan.domain.farm.model.request.ProductParam
import id.go.ngawikab.siketan.presentation.store.viewmodel.StoreViewModel
import id.go.ngawikab.siketan.utils.PrefManager
import id.go.ngawikab.siketan.utils.showCancelableDialog
import id.go.ngawikab.siketan.utils.showSuccessDialog
import id.go.ngawikab.siketan.utils.validation.IValidator
import id.go.ngawikab.siketan.utils.validation.NotEmptyTilValidator
import org.koin.android.ext.android.inject
import java.io.File

class StoreAddFragment : BaseFragment<FragmentStoreAddBinding>() {

    private val viewModel: StoreViewModel by inject()
    private val pref: PrefManager by inject()
    private val validators = mutableListOf<IValidator>()
    private var imageFile: File? = null
    private var unit = emptyString()
    private var productParam = ProductParam()


    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentStoreAddBinding = FragmentStoreAddBinding.inflate(layoutInflater)

    override fun initProcess() {
    }

    override fun initAction() {
        with(binding) {
            img.onClick {
                launcherPicker.launch(
                    ImagePickerConfig(
                        ImagePickerMode.SINGLE,
                        returnMode = ReturnMode.ALL
                    )
                )
            }

            btnCancel.onClick {showCancelableDialog()}

            btnSave.onClick {
                if (validateAll() && imageFile != null) sendToObservable()
                else if (imageFile == null) showToast("Foto tanaman tidak boleh kosong!")
                else if (unit.isNotEmpty()) showToast("Satuan tidak boleh kosong!")
                else return@onClick
            }

        }
    }

    override fun initUI() {
        with(binding) {
            imgBack.onClick { showCancelableDialog() }

            val user = pref.getUser()
            tilNik.setText(pref.getAttemptLogin().nik)
            tilName.setText(user.nama.toString())
            tilKecamatan.setText(user.kecamatanData?.nama.toString())
            tilDesa.setText(user.desaData?.nama.toString())

            // set units
            val units = resources.getStringArray(R.array.unit)
            val unitAdapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                units
            )

            autoComplete.apply {
                setAdapter(unitAdapter)
                setOnItemClickListener { adapterView, _, i, _ ->
                    unit = adapterView.getItemAtPosition(i).toString()
                }
            }
        }

        validation()
    }

    override fun initObservers() {
        with(binding) {
            viewModel.addProduct.observerLiveData(
                viewLifecycleOwner,
                onLoading = {
                    progress.visible()
                },
                onEmpty = {
                    progress.gone()
                },
                onFailure = { _, m ->
                    progress.gone()
                    showToast(m.toString())
                },
                onSuccess = {
                    progress.gone()
                    showSnackbarMessage(root, it.message)
                    showSuccessDialog("PRODUK")
                }
            )
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

    private fun sendToObservable() = with(binding) {
        val nik = tilNik.edittext
        val profesi = tilProfesi.edittext
        val productName = tilProductName.edittext
        val stok = tilStok.edittext.toInt()
        val harga = tilPrice.edittext
        val desc = tilDesc.toStringTrim()

        productParam = ProductParam(
            nik = nik,
            namaProduct = productName,
            profesiPenjual = profesi,
            stok = stok,
            satuan = unit,
            harga = harga,
            deskripsi = desc,
            fotoTanaman = imageFile!!
        )

        viewModel.addProduct(productParam)
    }

    private fun validation() {
        with(binding) {
            validators.add(NotEmptyTilValidator(tilNik.til, "NIK tidak boleh kosong"))
            validators.add(NotEmptyTilValidator(tilName.til, "Nama tidak boleh kosong"))
            validators.add(NotEmptyTilValidator(tilKecamatan.til, "Kecamatan tidak boleh kosong"))
            validators.add(NotEmptyTilValidator(tilDesa.til, "Desa tidak boleh kosong"))
            validators.add(NotEmptyTilValidator(tilProfesi.til, "Profesi tidak boleh kosong"))
            validators.add(
                NotEmptyTilValidator(
                    tilProductName.til,
                    "Nama produk tidak boleh kosong"
                )
            )
            validators.add(NotEmptyTilValidator(tilStok.til, "Stok tidak boleh kosong"))
            validators.add(NotEmptyTilValidator(tilPrice.til, "Harga tidak boleh kosong"))
        }
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