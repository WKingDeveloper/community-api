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
 * - Exception 추가 후 테스트 코드 작성
 *      v - 조회 시 해당 데이터가 존재하는지 확인 NotFound (board,post)
 *      - board 생성 시 parentId가 null이 아닌 경우 부모 id parentId가 null 인지 확인, 동일 그룹에서 indexNo 중복 있는지 확인
 *      - post 생성 시 boardId의 parentId가 null 아닌지
 * - board 응답 형태 변경
 * - user entity 추가
 * - post에 writerId (user) 추가 (datalodaer 적용)
 * - post queryDsl 적용
 * - reviewComment 추가
 */