package com.wkd.communityapi.model.common

enum class CommonState(val value: Int) {
    ACTIVE(1),

    INACTIVE(9),

    DELETED(0);

    companion object {
        private val values = values()
        fun parse(value: Int) = values.first { it.value == value }
        fun parseName(name: String) = values.first { it.name.equals(name, true) }
    }
}