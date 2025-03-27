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
                socket = aSocket(selectorManager).tcp().connect("10.100.102.12", 8080)
                writeChannel = socket.openWriteChannel(autoFlush = true)
                readChannel = socket.openReadChannel()
            }

            socketInitDeferred.complete(Unit)
        }
    }

    suspend fun sendMessage(data: String) {
        socketInitDeferred.await()

        writeChannel.writeStringUtf8("$data|")
    }

    private suspend fun ByteReadChannel.readUntilDelimiter(
        delimiter: Byte = '|'.code.toByte()
    ): String {
        val buffer = mutableListOf<Byte>()

        while (true) {
            val byte = readByte()

            if (byte == delimiter) {
                break
            }

            buffer.add(byte)
        }

        return buffer.toByteArray().decodeToString()
    }

    fun receiveMessagesFlow(): Flow<String> = flow {
        socketInitDeferred.await()

        while (true) {
            val message = readChannel.readUntilDelimiter()
            emit(message)
        }
    }
}