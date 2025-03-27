package org.dvir.bidbid

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.dvir.bidbid.presentation.chat.ChatRoomScreen
import org.dvir.bidbid.presentation.chat.ChatViewModel
import org.dvir.bidbid.presentation.login.LoginScreen
import org.dvir.bidbid.presentation.login.viewmodel.LoginViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var isLogin by remember { mutableStateOf(true) }

        if (isLogin) {
            LoginScreen(
                viewModel = remember { LoginViewModel() },
                onNavigate = {
                    isLogin = false
                }
            )
        } else {
            ChatRoomScreen(
                viewModel = remember { ChatViewModel() },
                onBack = {
                    isLogin = true
                }
            )
        }
    }
}