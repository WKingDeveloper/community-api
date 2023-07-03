package com.wkd.communityapi.graphql.scalar

import com.netflix.graphql.dgs.DgsScalar
import graphql.language.StringValue
import graphql.schema.Coercing
import graphql.schema.CoercingParseLiteralException
import graphql.schema.CoercingParseValueException
import graphql.schema.CoercingSerializeException
import java.time.LocalDateTime

@DgsScalar(name = "Timestamp")
class TimestampCoercing : Coercing<LocalDateTime, String> {
    override fun parseValue(input: Any): LocalDateTime {
        // input 값을 LocalDateTime으로 변환하는 로직을 작성합니다.
        return when (input) {
            is LocalDateTime -> input
            is String -> LocalDateTime.parse(input)
            else -> throw CoercingParseValueException("Cannot parse value as LocalDateTime: $input")
        }
    }

    override fun parseLiteral(input: Any): LocalDateTime {
        // input 값을 LocalDateTime으로 변환하는 로직을 작성합니다.
        return when (input) {
            is StringValue -> LocalDateTime.parse(input.value)
            else -> throw CoercingParseLiteralException("Cannot parse literal as LocalDateTime: $input")
        }
    }

    override fun serialize(dataFetcherResult: Any): String {
        // dataFetcherResult를 String으로 변환하는 로직을 작성합니다.
        return when (dataFetcherResult) {
            is LocalDateTime -> dataFetcherResult.toString()
            else -> throw CoercingSerializeException("Cannot serialize value as LocalDateTime: $dataFetcherResult")
        }
    }
}
