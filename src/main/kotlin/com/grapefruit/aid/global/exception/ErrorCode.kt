package com.grapefruit.aid.global.exception

enum class ErrorCode(
    val message: String,
    val status: Int
) {
    PASSWORD_MISMATCH("비밀번호가 일치하지 않습니다", 400),
    EXPIRED_TOKEN("토큰 만료", 401),
    INVALID_TOKEN("토큰 변질", 401),
    STORE_NOT_FOUND("가게를 찾을 수 없습니다", 404),
    SEAT_NOT_FOUND("좌석을 찾을 수 없습니다", 404),
    MENU_NOT_FOUND("메뉴를 찾을 수 없습니다", 404),
    USER_NOT_FOUND("유저를 찾을 수 없습니다", 404),
    SEAT_ALREADY_USED("이미 사용중인 좌석입니다", 409),
    SEAT_ALREADY_UNUSED("이 좌석은 이미 사용 가능 합니다", 409),
    DUPLICATE_USER_ID("유저의 id가 중복되었습니다", 409)
}