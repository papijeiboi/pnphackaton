package com.jemoje.pnphackaton.webervice

import com.google.gson.GsonBuilder
import com.jemoje.pnphackaton.model.UserResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {

    @FormUrlEncoded
    @POST("api/login")
    fun login(
        @Field("username") username:String,
        @Field("password") password: String
    ): retrofit2.Call<UserResponse>



    companion object Factory {
        var gson = GsonBuilder()
            .setLenient()
            .create()

        fun create(baseurl: String): UserService {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(UserService::class.java)
        }
    }
}