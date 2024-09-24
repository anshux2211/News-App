package com.example.newsapp

import com.example.newsapp.APIData.DataClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET("v2/top-headlines")
    suspend fun get_news_details(
        @Query("q") keyword: String,
        @Query("apiKey") apikey: String
    ):Response<DataClass>
}