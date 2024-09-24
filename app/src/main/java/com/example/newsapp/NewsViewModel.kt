package com.example.newsapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.APIData.DataClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(private val rpo:Repo):ViewModel() {

    val newslivedata=MutableLiveData<DataClass>()
    val isLoading=MutableLiveData<Boolean>(false)


     fun get_news_details(keyword:String,apiKey:String){

        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)

            val response = rpo.getnewslist(keyword, apiKey)
            Log.e("Error Response", "abc"+ response)
//                .errorBody()?.string() ?: "Unknown Error"
//            Log.i("response","re"+response)

            if (response.isSuccessful) {

                Log.i("Success_yes", "I got it")
                newslivedata.postValue(response.body())
                isLoading.postValue(false)
            } else {

                try {
                    val statusCode = response.code()  // Get HTTP status code (e.g., 403)
                    val headers = response.headers()  // Get HTTP headers
                    Log.e("Error Response", "Status Code: $statusCode")
                    Log.e("Error Response", "Headers: $headers")

                    val errorBody = response.errorBody()?.string()
                    if (errorBody != null) {
                        Log.e("Error Response", "Error Body: $errorBody")
                    } else {
                        Log.e("Error Response", "Error body is null")
                    }
                } catch (e: Exception) {
                    Log.e("Error Response", "Error reading response body", e)
                }
            }


        }
    }

}