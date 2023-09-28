package com.example.core.di

import androidx.room.Room
import com.example.core.data.DoaRepository
import com.example.core.data.local.DoaDatabase
import com.example.core.data.local.LocalDataSource
import com.example.core.data.network.ApiService
import com.example.core.data.network.NetworkDataSource
import com.example.core.domain.DoaInteractor
import com.example.core.domain.DoaUseCase
import com.example.core.domain.IDoaRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var coreModule = module {

    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(loggingInterceptor)

        clientBuilder.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://doa-doa-api-ahmadramadhan.fly.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(ApiService::class.java)
    }

    single {
        Room.databaseBuilder(androidContext(), DoaDatabase::class.java, "doa_data").build()
    }

    factory {
        get<DoaDatabase>().doaDao()
    }

    single {
        LocalDataSource(get())
    }

    single {
        NetworkDataSource(get())
    }

    single<IDoaRepository> {
        DoaRepository(get(), get())
    }

    single<DoaUseCase> {
        DoaInteractor(get())
    }
}