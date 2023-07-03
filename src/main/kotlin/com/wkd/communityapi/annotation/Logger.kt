package com.wkd.akiriapi.annotation

import org.slf4j.LoggerFactory

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Logger {
    companion object {
        inline var <reified T> T.logger: org.slf4j.Logger
            get() = LoggerFactory.getLogger(T::class.java)
            set(@Suppress("UNUSED_PARAMETER") value) {}
    }
}