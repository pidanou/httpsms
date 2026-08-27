package com.wayqast.gateway

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wayqast.gateway.ui.settings.SettingsScreen
import com.wayqast.gateway.ui.settings.SettingsViewModel
import com.wayqast.gateway.ui.theme.WayqastTheme
import timber.log.Timber

class SettingsActivity : AppCompatActivity() {
    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        viewModel.initialize(this)

        setContent {
            WayqastTheme {
                SettingsScreen(
                    viewModel = viewModel,
                    onBackClick = { onBackClicked() },
                    onLogoutClick = { onLogoutClick() }
                )
            }
        }
    }

    private fun onBackClicked() {
        Timber.d("back button clicked")
        redirectToMain()
    }

    private fun redirectToMain() {
        finish()
        val switchActivityIntent = Intent(this, MainActivity::class.java)
        startActivity(switchActivityIntent)
    }

    private fun onLogoutClick() {
        Timber.d("logout button clicked")
        MaterialAlertDialogBuilder(this)
            .setTitle("Confirm")
            .setMessage("Are you sure you want to logout of the Wayqast Gateway app?")
            .setNeutralButton("Cancel"){ _, _ -> Timber.d("logout dialog canceled") }
            .setPositiveButton("Logout"){_, _ ->
                Timber.d("logging out user")
                viewModel.logout(this) {
                    redirectToLogin()
                }
            }
            .show()
    }

    private fun redirectToLogin():Boolean {
        if (Settings.isLoggedIn(this)) {
            return false
        }
        val switchActivityIntent = Intent(this, LoginActivity::class.java)
        startActivity(switchActivityIntent)
        return true
    }
}
