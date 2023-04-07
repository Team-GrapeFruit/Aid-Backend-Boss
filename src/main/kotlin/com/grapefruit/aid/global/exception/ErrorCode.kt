package com.grapefruit.aid.global.exception

enum class ErrorCode(
    val message: String,
    val status: Int
) {
    STORE_NOT_FOUND("가게를 찾을 수 없습니다", 404),
    SEAT_NOT_FOUND("좌석을 찾을 수 없습니다", 404),
    MENU_NOT_FOUND("메뉴를 찾을 수 없습니다", 404),
    SEAT_ALREADY_USED("이미 사용중인 좌석입니다", 409),
    SEAT_ALREADY_UNUSED("이 좌석은 이미 사용 가능 합니다", 409),
    DUPLICATE_USER_ID("유저의 id가 중복되었습니다", 409)
}