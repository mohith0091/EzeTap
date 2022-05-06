package com.mohith.network

import com.mohith.network.model.UIModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface Api {
    @GET
    suspend fun fetchCustomUI(@Url url:String): Response<UIModel>

    @GET
     fun fetchImage(@Url url:String): Call<ResponseBody>


}