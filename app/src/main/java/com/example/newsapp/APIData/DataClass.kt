package com.example.newsapp.APIData

data class DataClass(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)