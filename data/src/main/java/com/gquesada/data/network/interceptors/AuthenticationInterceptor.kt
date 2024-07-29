package com.gquesada.data.network.interceptors

import io.ktor.client.HttpClient
import io.ktor.client.features.HttpClientFeature
import io.ktor.client.request.*
import io.ktor.http.HttpHeaders
import io.ktor.util.AttributeKey

class AuthenticationInterceptor private constructor() {

    class Config

    companion object Feature : HttpClientFeature<Config, AuthenticationInterceptor> {
        override val key: AttributeKey<AuthenticationInterceptor> =
            AttributeKey("AuthenticationInterceptor")

        override fun install(feature: AuthenticationInterceptor, scope: HttpClient) {
            scope.requestPipeline.intercept(HttpRequestPipeline.State) {
                // Add custom request interception logic here
                context.headers.apply {
                    append(HttpHeaders.Accept, "application/json")
                    append(HttpHeaders.Authorization, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4MWQyMDg5MTI3NWVhYjVmMTU4YzY1MzlhMjczNjk2NiIsIm5iZiI6MTcyMTc3MzIxNi44MTI5MjMsInN1YiI6IjU5OTIyMDg0OTI1MTQxMDQyYTAwNGZjOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.bkDo05O40xi7LEpW6c7tmBBKJh_aa2aP-69TYfZbrVc")
                }
                proceed()
            }
        }

        override fun prepare(block: Config.() -> Unit): AuthenticationInterceptor {
            return AuthenticationInterceptor()
        }
    }
}
