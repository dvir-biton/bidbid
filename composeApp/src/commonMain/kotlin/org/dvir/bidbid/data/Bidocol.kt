package org.dvir.bidbid.data

import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.Socket
import io.ktor.network.sockets.aSocket
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.readByte
import io.ktor.utils.io.writeStringUtf8
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.CompletableDeferred

val protocol = Bidocol()

class Bidocol {
    private val selectorManager = SelectorManager(Dispatchers.IO)
    private val socketInitDeferred = CompletableDeferred<Unit>()

    private lateinit var socket: Socket
    private lateinit var writeChannel: ByteWriteChannel
    private lateinit var readChannel: ByteReadChannel

    init {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                socket = aSocket(selectorManager).tcp().connect("192.168.252.236", 8080)
                writeChannel = socket.openWriteChannel(autoFlush = true)
                readChannel = socket.openReadChannel()
            }

            socketInitDeferred.complete(Unit)
        }
    }

    suspend fun sendMessage(code: RequestCode, data: String) {
        socketInitDeferred.await()
        val message = "${code.value}\$${data}|"

        writeChannel.writeStringUtf8(message)
    }

    private suspend fun ByteReadChannel.readUntilDelimiter(
        delimiter: Byte = '|'.code.toByte()
    ): String {
        val buffer = mutableListOf<Byte>()

        while (true) {
            val byte = readByte()
            if (byte == delimiter) break
            buffer.add(byte)
        }

        return buffer.toByteArray().toString()
    }

    fun receiveMessagesFlow(): Flow<Pair<ResponseCode, String>> = flow {
        socketInitDeferred.await()

        while (true) {
            val message = readChannel.readUntilDelimiter()

            val parts = message.split('$')

            if (parts.size >= 2) {
                emit(ResponseCode.fromValue(parts[0]) to parts[1])
            }
        }
    }
}