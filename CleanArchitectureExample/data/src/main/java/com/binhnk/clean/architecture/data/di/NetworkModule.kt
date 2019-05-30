package com.binhnk.clean.architecture.data.di

import com.binhnk.clean.architecture.data.Constants
import com.binhnk.clean.architecture.data.remote.api.UserApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { createHttpClient() }
    single { createWebService(get()) }
    single { createUserApi(get()) }
}

val mGson: Gson = GsonBuilder().setLenient().create()

fun createHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
    .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
    .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
    .build()

fun createWebService(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create(mGson))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()

fun createUserApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)