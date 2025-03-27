package org.dvir.bidbid

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.dvir.bidbid.presentation.LoginScreen
import org.dvir.bidbid.presentation.viewmodel.LoginViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        LoginScreen(
            viewModel = remember { LoginViewModel() }
        )
    }
}