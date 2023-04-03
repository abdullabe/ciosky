package com.example.ciosky.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    var basebannerurl = "https://jsonplaceholder.typicode.com/"
    var baseAddUser="https://dummyjson.com/"

    var okHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    val Api: ApiRequest by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(basebannerurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)
    }
    val ApiAddUser: ApiRequest by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseAddUser)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)
    }
}