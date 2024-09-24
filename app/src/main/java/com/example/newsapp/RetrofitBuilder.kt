package com.example.newsapp

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitBuilder {
    companion object{
        var retrofitServices:RetrofitServices?=null

        fun getInstance():RetrofitServices{
            if(retrofitServices==null){
                val retrofit= Retrofit.Builder()
                    .baseUrl("https://newsapi.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
                    .create(RetrofitServices::class.java)
                retrofitServices=retrofit
            }
            return retrofitServices!!
        }
    }
}