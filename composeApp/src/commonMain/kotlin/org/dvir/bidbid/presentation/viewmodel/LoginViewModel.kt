package org.dvir.bidbid.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import org.dvir.bidbid.data.RequestCode
import org.dvir.bidbid.data.ResponseCode
import org.dvir.bidbid.data.protocol

class LoginViewModel : ViewModel() {
    private var job: Job? = null

    private val _snackBarEvent = Channel<String>()
    val snackBarEvent = _snackBarEvent.consumeAsFlow()

    init {
        job = viewModelScope.launch {
            protocol.receiveMessagesFlow().collect {
                if (it.first == ResponseCode.LOGIN) {
                    // TODO: Navigate
                } else {
                    _snackBarEvent.send(it.second)
                }
            }
        }
    }

    fun onLogin(username: String) {
        viewModelScope.launch {
            protocol.sendMessage(RequestCode.LOGIN, username)
        }

        job?.cancel()
    }
}