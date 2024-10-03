package com.example.newsapp.API

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.APIDataClass.DataClass
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response

class Repo(private val retrofitServices: RetrofitServices) {
    private val ApiResponseMutableLiveData= MutableLiveData<NetworkResult<DataClass>>()
    val ApiResponseLiveData:LiveData<NetworkResult<DataClass>>
        get()=ApiResponseMutableLiveData
    suspend fun getnewslist(keyword:String,apiKey:String): Response<DataClass>? {
        ApiResponseMutableLiveData.postValue(NetworkResult.Loading())
        try {
            val response = retrofitServices.get_news_details(keyword, apiKey)
            if (response.isSuccessful && response.body() != null) {
                ApiResponseMutableLiveData.postValue(NetworkResult.Success(response.body()))
                return response
            }
            else
            {
                ApiResponseMutableLiveData.postValue(NetworkResult.Error("Failed to fetch data"))
                return null
            }

        }

        catch (e: IOException) {
            // Handle network-related exceptions like no internet connection
            ApiResponseMutableLiveData.postValue(NetworkResult.Error("Network Error"))
            Log.e("Network Error", "Failed to fetch news details: ${e.message}")
        } catch (e: HttpException) {
            // Handle HTTP-related errors (like 404, 500)
            ApiResponseMutableLiveData.postValue(NetworkResult.Error("HTTP Error"))
            Log.e("HTTP Error", "HTTP error occurred: ${e.message}")
        } catch (e: Exception) {
            // Handle any other unexpected exceptions
            ApiResponseMutableLiveData.postValue(NetworkResult.Error("An unexpected error occurred"))
            Log.e("Error", "An unexpected error occurred: ${e.message}")
        }

        return null
    }
}

