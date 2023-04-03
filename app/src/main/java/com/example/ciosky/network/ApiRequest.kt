package com.example.ciosky.network

import com.example.ciosky.model.ResponseModel
import com.example.ciosky.model.User.RequestAddUser
import com.example.ciosky.model.User.ResponseUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiRequest {
    @GET("posts/1")
    fun getResponse(): Call<ResponseModel>

    @POST("posts/1")
    fun AddUser(@Body request: RequestAddUser): Call<ResponseUser>
}