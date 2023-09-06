package com.wkd.communityapi.model.infrastructure;

/**
 * AuthorityLevel
 * 요청자의 권한 레벨
 */
enum class AuthorityLevel {
    /**
     * 비 로그인
     */
    UNKNOWN,

    /**
     * 일반 사용자(고객)
     */
    USER,

    /**
     * 관리자
     */
    ADMIN,

    /**
     * 시스템
     */
    SYSTEM;

    companion object {
        private val values = values()
        fun parse(value: String) = values.first { it.name.equals(value, true) }
    }
}
