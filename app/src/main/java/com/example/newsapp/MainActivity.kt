package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rpo: Repo
    private lateinit var newsviewmodel: NewsViewModel
    private lateinit var newsviewmodelfactory: NewsViewModelFactory

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        val keyword:String="tesla"
        val apiKey:String="ef54920fae7b4149a2119fa66c78b9c0"
        newsviewmodel.get_news_details(keyword,apiKey)

        newsviewmodel.newslivedata.observe(this) {
                Log.i("GETNEWSLOG", it.toString())

//            val tmp = mutableListOf<Article>()
//            var count:Int=0
//            for (item in it.articles) {
//                if(item.urlToImage!=null) {
//                    tmp.add(item)
//                    count=count+1
//                }
//                if(count==20)
//                    break
//            }
//
//            recyclerView.layoutManager = LinearLayoutManager(this)
//            val newsAdapter = NewsAdapter(tmp)
//            recyclerView.adapter = newsAdapter
        }


    }

    private fun init() {
        rpo = Repo(RetrofitBuilder.getInstance())
        newsviewmodelfactory = NewsViewModelFactory(rpo)
        newsviewmodel = ViewModelProvider(this, newsviewmodelfactory).get(NewsViewModel::class.java)

        recyclerView=findViewById(R.id.newsRecyclerView)

    }
}
