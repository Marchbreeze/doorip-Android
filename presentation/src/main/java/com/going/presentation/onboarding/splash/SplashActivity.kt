package com.going.presentation.onboarding.splash

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.going.domain.entity.AuthState
import com.going.presentation.R
import com.going.presentation.databinding.ActivitySplashBinding
import com.going.presentation.onboarding.signin.SignInActivity
import com.going.presentation.tendencytest.TendencyTestSplashActivity
import com.going.presentation.tripdashboard.TripDashBoardActivity
import com.going.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val viewModel by viewModels<SplashViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkConnectedNetwork()
    }

    private fun checkConnectedNetwork() {
        if (NetworkManager.checkNetworkState(this)) {
            initSplash()
        } else {
            showNetworkErrorAlertDialog()
        }
    }

    private fun initSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.clear()
            viewModel.getUserState()

            viewModel.userState.flowWithLifecycle(lifecycle).onEach { state ->
                when (state) {
                    AuthState.LOADING -> return@onEach
                    AuthState.SUCCESS -> navigateToDashBoardScreen()
                    AuthState.FAILURE -> navigateToSignInScreen()
                    AuthState.SIGNUP -> return@onEach
                    AuthState.SIGNIN -> return@onEach
                    AuthState.TENDENCY -> navigateToTendencyScreen()
                    AuthState.EMPTY -> return@onEach
                }
            }.launchIn(lifecycleScope)
        }, 3000)
    }

    private fun showNetworkErrorAlertDialog() =
        AlertDialog.Builder(this)
            .setTitle(R.string.notice)
            .setMessage(R.string.internet_connect_error)
            .setCancelable(false)
            .setPositiveButton(
                R.string.okay,
            ) { _, _ ->
                finishAffinity()
            }
            .create()
            .show()

    private fun navigateToDashBoardScreen() {
        Intent(this, TripDashBoardActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(this)
        }
        finish()
    }

    private fun navigateToTendencyScreen() {
        Intent(this, TendencyTestSplashActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(this)
        }
        finish()
    }

    private fun navigateToSignInScreen() {
        Intent(this, SignInActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(this)
        }
        finish()
    }
}
