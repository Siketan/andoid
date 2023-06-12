package com.wahidabd.siketan.presentation.auth.authentication

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.toStringTrim
import com.wahidabd.library.utils.exts.visible
import com.wahidabd.siketan.R
import com.wahidabd.siketan.databinding.FragmentLoginBinding
import com.wahidabd.siketan.domain.auth.model.LoginRequest
import com.wahidabd.siketan.presentation.MainActivity
import com.wahidabd.siketan.utils.PrefManager
import com.wahidabd.siketan.utils.emailMatches
import com.wahidabd.siketan.utils.navDirection
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel: AuthViewModel by viewModel()
    private val pref: PrefManager by inject()

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {
        with(binding) {
            tvCreateAccount.onClick {
                navDirection(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }
    }

    override fun initProcess() {
        with(binding) {
            btnlogin.onClick {
                val email = edtEmail.editText.toStringTrim()
                val password = edtPassword.editText.toStringTrim()
                val data = LoginRequest(email, password)

                when {
                    emailMatches(edtEmail.editText.toString()) -> showToast(getString(R.string.message_invalid_email))
                    edtPassword.editText.toStringTrim().isEmpty() -> showToast(getString(R.string.format_message_required, "Password"))
                    else -> viewModel.login(data)
                }
            }
        }
    }

    override fun initObservers() {
        viewModel.login.observerLiveData(
            viewLifecycleOwner,
            onEmpty = {},
            onLoading = { binding.progress.visible() },
            onFailure = { _, m ->
                binding.progress.gone()
                showToast(m.toString())
            },
            onSuccess = {
                binding.progress.gone()

                pref.login(true)
                pref.setToken(it.token.toString())

                MainActivity.start(requireContext())
                activity?.finish()
            }
        )
    }

}