package com.example.newsapp

import com.example.newsapp.APIData.DataClass
import retrofit2.Response

class Repo(private val retrofitServices: RetrofitServices) {
    suspend fun getnewslist(keyword:String,apiKey:String): Response<DataClass> {

        return retrofitServices.get_news_details(keyword,apiKey)
    }
}

