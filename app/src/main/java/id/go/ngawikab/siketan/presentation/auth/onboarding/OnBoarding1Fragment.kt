package id.go.ngawikab.siketan.presentation.auth.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.exts.onClick
import id.go.ngawikab.siketan.databinding.FragmentOnBoarding1Binding
import id.go.ngawikab.siketan.utils.navDirection

class OnBoarding1Fragment : BaseFragment<FragmentOnBoarding1Binding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentOnBoarding1Binding {
        return FragmentOnBoarding1Binding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {
        binding.btnNext.onClick {
            navDirection(OnBoarding1FragmentDirections.actionOnBoarding1FragmentToOnBoarding2Fragment())
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}

}