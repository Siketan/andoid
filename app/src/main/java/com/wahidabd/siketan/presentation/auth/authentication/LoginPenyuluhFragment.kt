package com.wahidabd.siketan.presentation.auth.authentication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.toStringTrim
import com.wahidabd.library.utils.exts.visible
import com.wahidabd.siketan.R
import com.wahidabd.siketan.data.auth.model.LoginPenyuluhRequest
import com.wahidabd.siketan.databinding.FragmentLoginPenyuluhBinding
import com.wahidabd.siketan.domain.auth.model.LoginRequest
import com.wahidabd.siketan.presentation.MainActivity
import com.wahidabd.siketan.utils.PrefManager
import com.wahidabd.siketan.utils.emailMatches
import com.wahidabd.siketan.utils.navDirection
import org.koin.android.ext.android.inject

class LoginPenyuluhFragment : BaseFragment<FragmentLoginPenyuluhBinding>() {

    private val pref: PrefManager by inject()
    private val viewModel: AuthViewModel by inject()

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentLoginPenyuluhBinding {
        return FragmentLoginPenyuluhBinding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {
        with(binding) {
            tvCreateAccount.onClick { navDirection(LoginFragmentDirections.actionLoginFragmentToRegisterFragment()) }
            loginUser.onClick { findNavController().navigateUp() }
        }
    }

    override fun initProcess() {
        with(binding) {
            btnlogin.onClick {
                val email = edtNik.editText.toStringTrim()
                val password = edtPassword.editText.toStringTrim()
                val data = LoginPenyuluhRequest(email, password)
                pref.setAttemptLoginPenyuluh(data)

                when {
                    emailMatches(edtNik.editText.toString()) -> showToast(getString(R.string.message_invalid_email))
                    edtPassword.editText.toStringTrim().isEmpty() -> showToast(getString(R.string.format_message_required, "Password"))
                    else -> viewModel.loginPenyuluh(data)
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

                debug { "${it.token}" }

                pref.login(true)
                pref.setToken(it.token.toString())
                pref.setUser(it.user!!)

                MainActivity.start(requireContext())
                activity?.finish()
            }
        )
    }
}