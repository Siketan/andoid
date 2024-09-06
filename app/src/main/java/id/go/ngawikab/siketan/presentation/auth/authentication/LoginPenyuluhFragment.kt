package id.go.ngawikab.siketan.presentation.auth.authentication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.toStringTrim
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.data.auth.model.LoginPenyuluhRequest
import id.go.ngawikab.siketan.databinding.FragmentLoginPenyuluhBinding
import id.go.ngawikab.siketan.presentation.MainActivity
import id.go.ngawikab.siketan.utils.PrefManager
import id.go.ngawikab.siketan.utils.common.SiketanBaseFragment
import id.go.ngawikab.siketan.utils.emailMatches
import id.go.ngawikab.siketan.utils.navDirection
import org.koin.android.ext.android.inject

class LoginPenyuluhFragment : SiketanBaseFragment<FragmentLoginPenyuluhBinding>() {

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
            onLoading = { showLoading() },
            onFailure = { _, m ->
                hideLoading()
                showToast(m.toString())
            },
            onSuccess = {
                hideLoading()

                debug { "Login Token --> ${it.token}" }

                pref.login(true)
                pref.setToken(it.token.toString()!!)
                pref.setUser(it.user!!)

                MainActivity.start(requireContext())
                activity?.finish()
            }
        )
    }
}