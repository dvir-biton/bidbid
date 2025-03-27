package org.dvir.bidbid.presentation.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.dvir.bidbid.data.protocol

class ChatViewModel : ViewModel() {
    private var job: Job? = null

    val messages = mutableStateListOf<String>()
    var currentMessage by mutableStateOf("")

    init {
        job = viewModelScope.launch {
            protocol.receiveMessagesFlow().collect {
                messages.add(it)
            }
        }
    }

    fun onMessageChange(message: String) {
        currentMessage = message
    }

    fun onSend() {
        viewModelScope.launch {
            protocol.sendMessage(currentMessage)
        }

        currentMessage = ""
    }
}