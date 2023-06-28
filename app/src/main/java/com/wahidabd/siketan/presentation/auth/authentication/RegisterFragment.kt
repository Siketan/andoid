package com.wahidabd.siketan.presentation.auth.authentication

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.toStringTrim
import com.wahidabd.library.utils.exts.visible
import com.wahidabd.siketan.R
import com.wahidabd.siketan.databinding.FragmentRegisterBinding
import com.wahidabd.siketan.domain.auth.model.RegisterRequest
import com.wahidabd.siketan.presentation.MainActivity
import com.wahidabd.siketan.utils.PrefManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

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
                when {
                    edtPassword.editText.toStringTrim() != edtPasswordConf.editText.toStringTrim() -> showToast(
                        getString(R.string.messsage_not_matches_password)
                    )
                    else -> reqToObservers()
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
                onLoading = { progress.visible() },
                onFailure = { _, m ->
                    progress.gone()
                    showToast(m.toString())
                },
                onSuccess = {
                    progress.gone()

                    pref.login(true)
                    pref.setToken(it.token.toString())

                    MainActivity.start(requireContext())
                    activity?.finish()
                }
            )
        }
    }

    private fun reqToObservers() = with(binding) {
        val nik = edtNik.editText.toStringTrim()
        val no_wa = edtWhatsapp.editText.toStringTrim()
        val nama = edtName.editText.toStringTrim()
        val password = edtPassword.editText.toStringTrim()

        val data = RegisterRequest(nik, no_wa, nama, password)
        viewModel.register(data)
    }

}