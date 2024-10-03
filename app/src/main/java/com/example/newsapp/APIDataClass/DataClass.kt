package com.example.newsapp.APIDataClass

data class DataClass(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)