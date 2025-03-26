package org.dvir.bidbid

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import org.dvir.bidbid.presentation.MontserratTypography
import org.dvir.project.presentation.LoginScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme(
        typography = MontserratTypography()
    ) {
        LoginScreen()
    }
}