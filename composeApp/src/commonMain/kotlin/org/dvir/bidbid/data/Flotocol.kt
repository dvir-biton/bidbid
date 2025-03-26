package org.dvir.project.data

import io.ktor.network.sockets.Socket
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.readByte
import io.ktor.utils.io.writeStringUtf8

class Bidocol {
    private suspend fun ByteReadChannel.readUntilDelimiter(delimiter: Byte = '|'.code.toByte()): String {
        val buffer = mutableListOf<Byte>()

        while (true) {
            val byte = readByte()
            if (byte == delimiter) {
                break
            }

            buffer.add(byte)
        }

        return buffer.toByteArray().toString()
    }

    suspend fun sendMessage(socket: Socket, code: String, data: String) {
        val channel = socket.openWriteChannel(autoFlush = true)
        val message = "$code\$${data}|"

        channel.writeStringUtf8(message)
    }

    suspend fun receiveMessage(socket: Socket): Pair<String, String> {
        val channel = socket.openReadChannel()
        val message = channel.readUntilDelimiter()
        val parts = message.split('$')

        return parts[0] to parts[1]
    }
}