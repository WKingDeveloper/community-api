package com.wkd.communityapi.graphql.directive

import com.netflix.graphql.dgs.DgsDirective
import com.netflix.graphql.dgs.context.DgsContext.Companion.getRequestData
import com.netflix.graphql.dgs.internal.DgsRequestData
import com.wkd.communityapi.exception.NotAuthorizedException
import com.wkd.communityapi.model.infrastructure.AuthorityLevel
import graphql.language.ArrayValue
import graphql.language.EnumValue
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import graphql.schema.GraphQLFieldDefinition
import graphql.schema.idl.SchemaDirectiveWiring
import graphql.schema.idl.SchemaDirectiveWiringEnvironment


@DgsDirective
class AuthDirectiveWiring : SchemaDirectiveWiring {
    companion object {
        const val DIRECTIVE_NAME = "auth"
        const val REQUIRES_ATTR = "requires"
    }

    override fun onField(environment: SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition>): GraphQLFieldDefinition? {
        val field = environment.element
        val parentType = environment.fieldsContainer

        // build a data fetcher that first checks authorisation roles before then calling the original data fetcher
        // 'getDirective(String!): GraphQLDirective!' is deprecated. Overrides deprecated member in 'graphql.schema.GraphQLDirectiveContainer'. Deprecated in Java
        val originalDataFetcher = environment.codeRegistry.getDataFetcher(parentType, field)
        if (field.getDirective(DIRECTIVE_NAME) == null) {
            return field
        }

        // 'getter for argumentValue: InputValueWithState' is deprecated. Deprecated in Java
        val authFetcher = DataFetcher { dataFetchingEnvironment ->
            val argValue = dataFetchingEnvironment.fieldDefinition.getDirective(DIRECTIVE_NAME)
                .getArgument(REQUIRES_ATTR).argumentValue.value as ArrayValue
            val requires = argValue.values.map { AuthorityLevel.valueOf((it as EnumValue).name) }.toTypedArray()
            execute(dataFetchingEnvironment, requires, originalDataFetcher)
        }

        // now change the field definition to have the new authorising data fetcher
        environment.codeRegistry.dataFetcher(parentType, field, authFetcher)
        return field
    }


    private fun execute(
        env: DataFetchingEnvironment,
        requires: Array<AuthorityLevel>?,
        originalDataFetcher: DataFetcher<*>
    ): Any {
        val requestData: DgsRequestData? = getRequestData(env)
        val authorityLevel = requestData?.headers!!.getFirst("xf-authority-level") ?: "UNKNOWN"

        requires?.run {
            validateAuthority(authorityLevel, appendSystemLevel(requires))
        }
        return originalDataFetcher.get(env)
    }

    private fun validateAuthority(authorityLevel: String, requires: Array<AuthorityLevel>) {
        if (!requires.contains(AuthorityLevel.valueOf(authorityLevel))) {
            throw NotAuthorizedException(requires, AuthorityLevel.valueOf(authorityLevel))
        }
    }

    /**
     * SYSTEM 권한은 모든 권한을 가지고 있기 때문에 누락이 되었더라도 자동으로 추가 한다.
     */
    private fun appendSystemLevel(requires: Array<AuthorityLevel>): Array<AuthorityLevel> {
        if (!requires.contains(AuthorityLevel.SYSTEM)) {
            return requires + AuthorityLevel.SYSTEM
        }
        return requires
    }
}
