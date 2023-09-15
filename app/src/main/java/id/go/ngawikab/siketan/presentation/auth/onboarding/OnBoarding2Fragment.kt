package id.go.ngawikab.siketan.presentation.auth.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.exts.onClick
import id.go.ngawikab.siketan.databinding.FragmentOnBoarding2Binding
import id.go.ngawikab.siketan.utils.navDirection

class OnBoarding2Fragment : BaseFragment<FragmentOnBoarding2Binding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentOnBoarding2Binding {
        return FragmentOnBoarding2Binding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {
        binding.btnNext.onClick {
            navDirection(OnBoarding2FragmentDirections.actionOnBoarding2FragmentToLoginFragment())
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}

}