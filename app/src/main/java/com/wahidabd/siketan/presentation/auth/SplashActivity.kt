package com.wahidabd.siketan.presentation.auth

import android.annotation.SuppressLint
import android.os.CountDownTimer
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.siketan.databinding.ActivitySplashBinding
import com.wahidabd.siketan.presentation.MainActivity
import com.wahidabd.siketan.presentation.auth.authentication.AuthViewModel
import com.wahidabd.siketan.utils.PrefManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private val pref: PrefManager by inject()
    private val viewModel: AuthViewModel by viewModel()

    override fun getViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {
        val time = 2000
        with(binding) {
            progress.max = time

            object : CountDownTimer(time.toLong(), 1000) {
                override fun onTick(p0: Long) {
                    progress.progress = p0.toInt()
                }


                override fun onFinish() {
                    if (pref.getLogin()) {
                        viewModel.login(pref.getAttemptLogin())
                        viewModel.login.observerLiveData(
                            this@SplashActivity,
                            onLoading = {},
                            onEmpty = {},
                            onFailure = { _, m ->
                                showToast(m.toString())
                                AuthActivity.start(this@SplashActivity)
                                finish()
                            },
                            onSuccess = {
                                pref.setToken(it.token.toString())
                                pref.setUser(it.user!!)
                                MainActivity.start(this@SplashActivity)
                                finish()
                            }
                        )
                    } else {
                        AuthActivity.start(this@SplashActivity)
                        finish()
                    }
                }
            }.start()
        }
    }

    override fun initObservers() {}

}