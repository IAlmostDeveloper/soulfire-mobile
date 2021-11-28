package ru.ialmostdeveloper.soulfire_mobile.di

import ru.ialmostdeveloper.soulfire_mobile.network.APIService
import ru.ialmostdeveloper.soulfire_mobile.network.RequestsManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.ialmostdeveloper.soulfire_mobile.storage.Storage
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRequestsManager(service: APIService?): RequestsManager {
        return RequestsManager(service!!)
    }

    @Provides
    @Singleton
    fun provideRetrofit(storage: Storage): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(storage.readServerUrl())
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }
}