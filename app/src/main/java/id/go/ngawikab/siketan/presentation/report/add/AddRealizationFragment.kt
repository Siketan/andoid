package id.go.ngawikab.siketan.presentation.report.add

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.clear
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.toStringTrim
import com.wahidabd.library.utils.exts.visible
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.data.farm.model.farm.request.InputTanamanRequest
import id.go.ngawikab.siketan.databinding.FragmentAddRealizationBinding
import id.go.ngawikab.siketan.presentation.report.viewmodel.ReportViewModel
import id.go.ngawikab.siketan.utils.CategoryType
import id.go.ngawikab.siketan.utils.HarverstType
import id.go.ngawikab.siketan.utils.PlantType
import id.go.ngawikab.siketan.utils.PrefManager
import id.go.ngawikab.siketan.utils.UserRole
import id.go.ngawikab.siketan.utils.common.SiketanBaseFragment
import id.go.ngawikab.siketan.utils.showCancelableDialog
import id.go.ngawikab.siketan.utils.showSuccessDialog
import org.koin.android.ext.android.inject

class AddRealizationFragment : SiketanBaseFragment<FragmentAddRealizationBinding>() {

    private val pref: PrefManager by inject()
    private val viewModel: ReportViewModel by inject()
    private val data: AddRealizationFragmentArgs by navArgs()


    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentAddRealizationBinding =
        FragmentAddRealizationBinding.inflate(layoutInflater)


    override fun initUI() {
        with(binding) {
            val user = pref.getUser()
            if (user.role == UserRole.PETANI.role){
                tilNik.setText(pref.getAttemptLogin().nik)
                tilName.setText(user.nama.toString())
                tilKecamatan.setText(user.kecamatanData?.nama.toString())
                tilDesa.setText(user.desaData?.nama.toString())
            }else {
                tilNik.setText(data.user.nik.toString())
                tilName.setText(data.user.nama.toString())
                tilKecamatan.setText(data.user.kecamatanData?.nama.toString())
                tilDesa.setText(data.user.desaData?.nama.toString())
            }
        }

        setupStatusLahan()
        setupKategori()
        setupJenisTanaman()
        setupMusimTanam()
        setupBulan()
    }

    override fun initAction() {
        with(binding) {
            imgBack.onClick { showCancelableDialog() }
            btnCancel.onClick { showCancelableDialog() }

        }
    }

    override fun initProcess() {
        with(binding) {
            btnSubmit.onClick {
                val id = when {
                    (pref.getUser().role == UserRole.PENYULUH.role) -> data.user.id
                    else -> pref.getUser().id
                }
                val statusLahan = if (tilStatusLahan.toStringTrim() == "Lahan Sendiri") {
                    "MILIK SENDIRI"
                } else {
                    "TANAH SEWA"
                }
                val luasLahan = tilLuasLahan.edittext
                val kategori = tilKategori.toStringTrim()
                val jenisTanaman = tilJenisTanaman.toStringTrim()
                val jenisPanen = tilJenisPanen.toStringTrim()
                val komoditas = tilKomoditas.toStringTrim()
                val bulanTanam = tilBulanTanam.toStringTrim()
                val bulanPanen = tilBulanPanen.toStringTrim()
                val produksiPanen = tilProduksiPanen.edittext
                val periodeMusimTanam = if (tilMusimTanam.toStringTrim() == "Musiman") {
                    "Tanaman Semusim"
                } else {
                    "Tanaman Tahunan"
                }

                val data = InputTanamanRequest(
                    fk_petaniId = id!!,
                    statusKepemilikanLahan = statusLahan,
                    luasLahan = luasLahan,
                    jenis = jenisPanen,
                    kategori = kategori,
                    komoditas = komoditas,
                    periodeMusimTanam = periodeMusimTanam,
                    periodeBulanTanam = bulanTanam,
                    prakiraanBulanPanen = bulanPanen,
                    prakiraanProduksiPanen = produksiPanen.toInt(),
                    prakiraanLuasPanen = produksiPanen.toInt(),
                )

                viewModel.addTanaman(data)
            }
        }
    }

    override fun initObservers() {
        viewModel.addTanaman.observerLiveData(
            viewLifecycleOwner,
            onLoading = {
                showLoading()
            },
            onEmpty = {
                hideLoading()
            },
            onFailure = { _, message ->
                hideLoading()
                showToast(message.toString())
            },
            onSuccess = {
                hideLoading()
                showSuccessDialog("TANAMAN")
            }
        )
    }

    private fun setupBulan() = with(binding) {
        val list = resources.getStringArray(R.array.month)
        setArrayAdapter(list, autoCompleteBulanTanam, onclick = {})
        setArrayAdapter(list, autoCompleteBulanPanen, onclick = {})
    }

    private fun setupStatusLahan() = with(binding) {
        val list = resources.getStringArray(R.array.land_status)
        setArrayAdapter(list, autoCompleteStatusLahan, onclick = {

        })
    }

    private fun setupKategori() = with(binding) {
        val list = resources.getStringArray(R.array.category)
        setArrayAdapter(list, autoCompleteKategori, onclick = {
            when (it) {
                CategoryType.PANGAN.type -> {
                    tilJenisTanaman.gone()
                    tilJenisPanen.gone()
                    tilBulanTanam.visible()
                    clearTextInputLayout()

                    val komoditas = resources.getStringArray(R.array.food)
                    setupKomoditas(komoditas)
                }

                CategoryType.PERKEBUNAN.type -> {
                    tilJenisTanaman.gone()
                    tilJenisPanen.visible()
                    clearTextInputLayout()

                    setupJenisPanen(CategoryType.PERKEBUNAN.type)
                    val komoditas = resources.getStringArray(R.array.food)
                    setupKomoditas(komoditas)
                }

                CategoryType.HOLTIKULTURA.type -> {
                    tilBulanTanam.visible()
                    tilJenisTanaman.visible()
                    tilJenisPanen.gone()
                    clearTextInputLayout()
                }
            }
        })
    }

    private fun setupJenisTanaman() = with(binding) {
        val list = resources.getStringArray(R.array.plant_type)
        setArrayAdapter(list, autoCompleteJenisTanaman, onclick = {
            when (it) {
                PlantType.FRUIT.type -> {
                    tilJenisPanen.visible()

                    setupJenisPanen(PlantType.FRUIT.type)
                }

                PlantType.VEGENT.type -> {
                    tilJenisPanen.gone()
                    tilJenisPanen.clear()

                    val komoditas = resources.getStringArray(R.array.holtikultura_vegetable)
                    setupKomoditas(komoditas)
                }
            }
        })
    }

    private fun setupJenisPanen(type: String) = with(binding) {
        val list = resources.getStringArray(R.array.harvest_type)
        setArrayAdapter(list, autoCompleteJenisPanen, onclick = {
            if (it == HarverstType.MUSIMAN.type && type == CategoryType.PERKEBUNAN.type) {
                val komoditas = resources.getStringArray(R.array.season_harvest_type)
                setupKomoditas(komoditas)
            }
            if (it == HarverstType.TAHUNAN.type && type == CategoryType.PERKEBUNAN.type) {
                val komoditas = resources.getStringArray(R.array.annual_harvest_type)
                setupKomoditas(komoditas)
            }
            if (it == HarverstType.MUSIMAN.type && type == PlantType.FRUIT.type) {
                val komoditas = resources.getStringArray(R.array.season_holtikultura_fruit)
                setupKomoditas(komoditas)
            }
            if (it == HarverstType.TAHUNAN.type && type == PlantType.FRUIT.type) {
                val komoditas = resources.getStringArray(R.array.annual_holtikultura_fruit)
                setupKomoditas(komoditas)
            }
        })
    }

    private fun setupKomoditas(data: Array<String>) = with(binding) {
        setArrayAdapter(data, autoCompleteKomoditas, onclick = {})
    }

    private fun setupMusimTanam() = with(binding) {
        val list = resources.getStringArray(R.array.growing_season)
        setArrayAdapter(list, autoCompleteMusimTanam) {}
    }

    private fun setArrayAdapter(
        list: Array<String>,
        autoComplete: MaterialAutoCompleteTextView,
        onclick: (String) -> Unit
    ) {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            list
        )
        autoComplete.setAdapter(adapter)
        autoComplete.setOnItemClickListener { adapterView, _, i, _ ->
            onclick.invoke(adapterView.getItemAtPosition(i).toString())
        }
    }

    private fun clearTextInputLayout() = with(binding) {
        tilJenisTanaman.clear()
        tilJenisPanen.clear()
        tilBulanTanam.clear()
    }

}