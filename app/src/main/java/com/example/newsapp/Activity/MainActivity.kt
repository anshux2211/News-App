package com.example.newsapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.API.NetworkResult
import com.example.newsapp.API.NewsViewModel
import com.example.newsapp.API.NewsViewModelFactory
import com.example.newsapp.API.Repo
import com.example.newsapp.API.RetrofitBuilder
import com.example.newsapp.APIDataClass.Article
import com.example.newsapp.Adapters.NewsAdapter
import com.example.newsapp.Adapters.NewsCategoryAdaptor
import com.example.newsapp.R

class MainActivity : AppCompatActivity() {
    // Declaration
    private lateinit var rpo: Repo
    private lateinit var newsviewmodel: NewsViewModel
    private lateinit var newsviewmodelfactory: NewsViewModelFactory

    private lateinit var recyclerView: RecyclerView
    private lateinit var catgory_recycler_view:RecyclerView
    private lateinit var progress_bar:ProgressBar
    private lateinit var errorView:TextView
    private lateinit var btn_retry:Button
    private lateinit var edt_seach:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Splash screen
        Thread.sleep(1000)
        installSplashScreen()

        setContentView(R.layout.activity_main)

        init()

        var idx:Int=0
        var keyword:String="headline"
        val apiKey:String="ef54920fae7b4149a2119fa66c78b9c0"
        newsviewmodel.get_news_details(keyword,apiKey)

        catgory_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) // Horizontal Layout Manager
        val CategoryNewsAdapterInstance= NewsCategoryAdaptor(generate_category(),idx)
        catgory_recycler_view.adapter = CategoryNewsAdapterInstance

        // Handled Category Recycler View Item Click Listener
        CategoryNewsAdapterInstance.setOnClickListener(object :
            NewsCategoryAdaptor.OnClickListener {
            override fun onClick(position: Int, item: String) {
                Toast.makeText(this@MainActivity, "$item", Toast.LENGTH_SHORT).show()
                idx=position
                keyword=item
                newsviewmodel.get_news_details(keyword,apiKey)
            }
        })

        // Handled Searching API Call
        edt_seach.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                val text = edt_seach.text.toString() // Extract text from EditText
                keyword=edt_seach.text.toString()
                newsviewmodel.get_news_details(keyword,apiKey)
                true
            } else {
                false
            }
        }

        // Observing Data Fetched from API
        newsviewmodel.apiResponseLiveData.observe(this) {flag->
            when(flag) {

                is NetworkResult.Success -> {
                    newsviewmodel.newslivedata.observe(this) {
                        Log.i("GETNEWSLOG", it.toString())

                        val tmp = mutableListOf<Article>()
                        for (item in it.articles) {
                            if (item.urlToImage != null) {
                                tmp.add(item)
                            }
                        }

                        if(tmp.size==0){
                            errorView.text="0 Results"
                            view_visibility(true,false,true,false)
                        }
                        else {
                            view_visibility(true,false,false,false)

                            recyclerView.layoutManager = LinearLayoutManager(this)
                            val newsAdapter = NewsAdapter(tmp)
                            recyclerView.adapter = newsAdapter

                            // Handled Recycler View Item Click Listener
                            newsAdapter.setOnClickListener(object :
                                NewsAdapter.OnClickListener {
                                override fun onClick(position: Int, item: Article) {
                                    // Activity Calling
                                    val intent = Intent(recyclerView.context, Detail_News::class.java)
                                    intent.putExtra("url", item.url)
                                    recyclerView.context.startActivity(intent)
                                }
                            })
                        }
                    }
                }

                is NetworkResult.Error->{
                    Log.i("CheckError",""+flag.message)
                    errorView.text=flag.message
                    view_visibility(false,false,true,true)
                    btn_retry.setOnClickListener {
                        newsviewmodel.get_news_details(keyword,apiKey)
                    }
                }

                is NetworkResult.Loading->{
                    view_visibility(false,true,false,false)
                }
            }
        }
    }

    private fun init() {
        rpo = Repo(RetrofitBuilder.getInstance())
        newsviewmodelfactory = NewsViewModelFactory(rpo)
        newsviewmodel = ViewModelProvider(this, newsviewmodelfactory).get(NewsViewModel::class.java)

        recyclerView=findViewById(R.id.newsRecyclerView)
        progress_bar=findViewById(R.id.progressBar)
        errorView=findViewById(R.id.error_view)
        btn_retry=findViewById(R.id.btn_retry)
        catgory_recycler_view=findViewById(R.id.categoryRecyclerView)
        edt_seach=findViewById(R.id.edt_search)
    }

    private fun generate_category():List<String>{
        val result: MutableList<String> = mutableListOf()
        result.add("Headlines")
        result.add("India")
        result.add("Stocks")
        result.add("Politics")
        result.add("Sports")
        result.add("Health")
        result.add("Technology")
        result.add("Economy")
        result.add("Science")
        result.add("Banking")
        result.add("Wealth")
        return result
    }

    private fun view_visibility( rec:Boolean, pro:Boolean,txt:Boolean,rtry:Boolean){
        if(rec)
            recyclerView.visibility=View.VISIBLE
        else
            recyclerView.visibility=View.GONE

        if(pro)
            progress_bar.visibility=View.VISIBLE
        else
            progress_bar.visibility=View.GONE

        if(txt)
            errorView.visibility=View.VISIBLE
        else
            errorView.visibility=View.GONE

        if(rtry)
            btn_retry.visibility=View.VISIBLE
        else
            btn_retry.visibility=View.GONE
    }
}
