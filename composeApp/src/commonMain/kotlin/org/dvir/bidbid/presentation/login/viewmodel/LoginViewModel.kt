package org.dvir.bidbid.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import org.dvir.bidbid.data.protocol

class LoginViewModel : ViewModel() {
    private val _navigateEvent = Channel<Unit>()
    val navigateEvent = _navigateEvent.consumeAsFlow()


    fun onLogin(username: String) {
        viewModelScope.launch {
            protocol.sendMessage(username)

            _navigateEvent.send(Unit)
        }
    }
}