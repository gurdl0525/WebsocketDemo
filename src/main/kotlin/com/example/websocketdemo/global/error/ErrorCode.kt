package com.example.websocketdemo.global.error

enum class ErrorCode(
    private val status: Int,
    private val code: String,
    private val message: String
){

    // Bad Request
    BAD_REQUEST(400, "B01", "잘못된 요청"),

    // Un Authorized
    INVALID_TOKEN(401, "U01", "유효하지 않은 토큰"),
    EXPIRED_TOKEN(401, "U02", "토큰의 사용기간이 만료되었습니다"),
    UN_AUTHORIZED(401, "U03", "유효한 인증 정보가 부족하여 요청이 거부되었습니다"),

    // Not Found
    USER_NOT_FOUND(404, "N01", "유저를 찾을 수 없습니다"),

    // Conflict
    DUPLICATED_ACCOUNT_ID(409, "C01", "중복된 아이디입니다"),

    // Internal Sever Error
    INTERNAL_SERVER_GLOBAL_ERROR(500, "I01", "Internal Server Error")
    ;

    fun status() = this.status

    fun code() = this.code

    fun message() = this.message

}
