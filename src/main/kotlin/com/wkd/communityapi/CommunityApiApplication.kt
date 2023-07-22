package com.wkd.communityapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CommunityApiApplication

fun main(args: Array<String>) {
    runApplication<CommunityApiApplication>(*args)
}

/**
 * Todo
 * - board 응답 형태 변경
 * - user entity 추가
 *      - spring security, JWT , Schema 권한
 *      - dataloader 적용 (post)
 * - post에 writerId (user) 추가 (datalodaer 적용)
 * - post queryDsl 적용
 * - reviewComment 추가
 */