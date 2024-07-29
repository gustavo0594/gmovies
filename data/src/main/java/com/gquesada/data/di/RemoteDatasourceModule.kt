package com.gquesada.data.di

import android.content.Context
import com.gquesada.data.network.interceptors.AuthenticationInterceptor
import com.gquesada.data.network.services.URLServices.BASE_PATH
import com.gquesada.data.network.services.URLServices.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object RemoteDatasourceModule {

    @Provides
    fun provideHttpClient(
        @ApplicationContext context: Context,
    ): HttpClient {
        return HttpClient(Android) {
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL
                    // Uncomment if a base path is also needed
                   // encodedPath = BASE_PATH
                }
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
            install(AuthenticationInterceptor)
        }

    }
}