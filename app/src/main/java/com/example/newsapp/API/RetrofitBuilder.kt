package com.example.newsapp.API

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    companion object{
        var retrofitServices: RetrofitServices?=null

        fun getInstance(): RetrofitServices {
            if(retrofitServices ==null){
                val client = OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                            .build()
                        chain.proceed(request)
                    }
                    .build()

                val retrofit= Retrofit.Builder()
                    .baseUrl("https://newsapi.org")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
                    .create(RetrofitServices::class.java)
                retrofitServices =retrofit
            }
            return retrofitServices!!
        }
    }
}