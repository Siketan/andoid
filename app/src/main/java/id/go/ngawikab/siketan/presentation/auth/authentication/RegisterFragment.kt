package id.go.ngawikab.siketan.presentation.auth.authentication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.toStringTrim
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.databinding.FragmentRegisterBinding
import id.go.ngawikab.siketan.domain.auth.model.RegisterBaseData
import id.go.ngawikab.siketan.domain.auth.model.RegisterRequest
import id.go.ngawikab.siketan.utils.PrefManager
import id.go.ngawikab.siketan.utils.common.SiketanBaseFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterFragment : SiketanBaseFragment<FragmentRegisterBinding>() {

    private val viewModel: AuthViewModel by viewModel()
    private val pref: PrefManager by inject()


    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentRegisterBinding {
        return FragmentRegisterBinding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {


        with(binding) {

            btnlogin.onClick {
                val nik = edtNik.editText.toStringTrim()
                val no_wa = edtWhatsapp.editText.toStringTrim()
                val nama = edtName.editText.toStringTrim()
                val password = edtPassword.editText.toStringTrim()
                val baseData = RegisterBaseData(
                    nik,
                    no_wa,
                    nama,
                    password
                )
                when {
                    edtPassword.editText.toStringTrim() != edtPasswordConf.editText.toStringTrim() -> showToast(
                        getString(R.string.messsage_not_matches_password)
                    )

                    else ->
                    {
                        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToRegisterDataFragment(baseData))
                    }

                }
            }

        }
    }

    override fun initProcess() {}

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
                }
            )
        }
    }


}