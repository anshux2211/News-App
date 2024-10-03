package com.example.newsapp.API

import com.example.newsapp.APIDataClass.DataClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET("/v2/everything")
    suspend fun get_news_details(
        @Query("q") keyword: String,
        @Query("apiKey") apikey: String
    ):Response<DataClass>
}