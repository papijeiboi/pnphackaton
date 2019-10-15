package com.jemoje.pnphackaton.webervice

import com.google.gson.GsonBuilder
import com.jemoje.pnphackaton.model.PlateData
import com.jemoje.pnphackaton.model.PlateResponse
import com.jemoje.pnphackaton.model.ScanStaffResponse
import com.jemoje.pnphackaton.model.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

interface UserService {

    @FormUrlEncoded
    @POST("api/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<UserResponse>

    @GET("api/mobile/index")
    fun getPlateNumber(
        @Query("search") search: String?,
        @Header("Accept") accept: String,
        @Header("Authorization") authHeader: String
    ): Call<MutableList<PlateData>>

    @GET("api/mobile/scan/application/{code}")
    fun scanStaff(
        @Path("code") qrCode:String?,
        @Header("Authorization") authHeader: String
    ): Call<ScanStaffResponse>


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