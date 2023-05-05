package com.example.websocketdemo.global.error

enum class ErrorCode(
    private val status: Int,
    private val code: String,
    private val message: String
){

    // Bad Request
    BAD_REQUEST(400, "B01", "Bad Request"),

    // Un Authorized
    INVALID_TOKEN(401, "U01", "Invalid Token"),
    EXPIRED_TOKEN(401, "U02", "Expired Token"),
    UN_AUTHORIZED(401, "U03", "Un Authenticated"),

    // Not Found
    USER_NOT_FOUND(404, "N01", "User Not Found"),

    // Conflict
    DUPLICATED_ACCOUNT_ID(409, "C01", "Duplicated AccountId"),

    // Internal Sever Error
    INTERNAL_SERVER_GLOBAL_ERROR(500, "I01", "Internal Server Error")
    ;

    fun status() = this.status

    fun code() = this.code

    fun message() = this.message

}
