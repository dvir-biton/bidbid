package org.dvir.bidbid.data

enum class ResponseCode(val value: String) {
    ERROR("0"),

    LOGIN("5"),

    NEW_AUCTION("10"),
    BID("11"),
    AUCTION_END("12");

    companion object {
        fun fromValue(value: String): ResponseCode {
            for (code in entries) {
                if (code.value == value) {
                    return code
                }
            }

            throw IllegalArgumentException("No such code: $value")
        }
    }
}