package id.go.ngawikab.siketan.presentation.auth.authentication

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.clear
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.toStringTrim
import id.go.ngawikab.siketan.databinding.FragmentRegisterDataBinding
import id.go.ngawikab.siketan.domain.auth.model.RegisterBaseData
import id.go.ngawikab.siketan.domain.auth.model.RegisterRequest
import id.go.ngawikab.siketan.presentation.report.viewmodel.AddressViewModel
import id.go.ngawikab.siketan.utils.PrefManager
import id.go.ngawikab.siketan.utils.common.SiketanBaseFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterDataFragment : SiketanBaseFragment<FragmentRegisterDataBinding>() {

    private val viewModel: AuthViewModel by viewModel()
    private val pref: PrefManager by inject()
    private val addressViewModel: AddressViewModel by inject()

    private var kecValue: String? = null
    private var desaValue: String? = null
    private var namaKelompokValue: String? = null
    private var penyuluhIdValue: Int? = null
    private var alamatValue: String? = null
    private var gapoktanValue: String? = null


    private val args: RegisterDataFragmentArgs by navArgs()

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentRegisterDataBinding {
        return FragmentRegisterDataBinding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {
        with(binding) {
            btnlogin.onClick {
                reqToObservers()
            }

        }
    }

    override fun initProcess() {
        setKecamatan()
        setPenyuluh()
    }

    override fun initObservers() {
        with(binding) {
            viewModel.register.observerLiveData(
                viewLifecycleOwner,
                onEmpty = {},
                onLoading = { showLoading() },
                onFailure = { _, m ->
                    hideLoading()
                    showToast(m.toString())
                },
                onSuccess = {
                    hideLoading()
                    findNavController().navigateUp()
                    findNavController().navigateUp()
                    showToast("Mohon tunggu data anda diaktivasi oleh admin.")
                }
            )
        }
    }

    private fun reqToObservers() = with(binding) {

        val nik = args.baseData?.nik
        val no_wa = args.baseData?.no_wa
        val nama = args.baseData?.nama
        val password = args.baseData?.password
        alamatValue = edtAddress.editText.toStringTrim()
        val data = RegisterRequest(
            NIK = nik ?: "",
            no_wa = no_wa ?: "",
            nama = nama ?: "",
            password = password ?: "",
            alamat = alamatValue ?: "",
            desa = desaValue ?: "",
            kecamatan = kecValue ?: "",
            gapoktan = edtGapoktan.editText.toStringTrim(),
            penyuluh = penyuluhIdValue ?: 0,
            namaKelompok = namaKelompokValue ?: ""
        )
        viewModel.register(data)

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

    private fun setPenyuluh() = with(binding) {
        viewModel.penyuluh()
        viewModel.penyuluh.observerLiveData(
            viewLifecycleOwner,
            onLoading = {},
            onEmpty = {},
            onFailure = { _, _ -> },
            onSuccess = {
                val res = it
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    res.map { f -> f.nama }
                )
                penyuluh.apply {
                    setAdapter(adapter)
                    setOnItemClickListener { _, _, i, _ ->
                        penyuluhIdValue = res[i].id
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
                        setKelompokTani(desaValue!!)

                    }
                }
            }
        )
    }

    private fun setGapoktan(gapoktanName: String) {
        with(binding) {
            edtGapoktan.editText.setText(gapoktanName)
            gapoktanValue = gapoktanName

        }
    }

    private fun setKelompokTani(desa: String) = with(binding) {
        viewModel.kelompokTani(desa)
        viewModel.kelompokTani.observerLiveData(
            viewLifecycleOwner,
            onLoading = {},
            onEmpty = {},
            onFailure = { _, _ -> },
            onSuccess = {
                val res = it
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    res.map { f -> f.namaKelompok }
                )
                kelompokTani.apply {
                    setGapoktan(it[0].gapoktan ?: "")
                    setAdapter(adapter)
                    setOnItemClickListener { _, _, i, _ ->
                        namaKelompokValue = res[i].namaKelompok
                    }
                }
            }
        )
    }


}