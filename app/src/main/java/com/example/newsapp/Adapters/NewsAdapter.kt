package com.example.newsapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.APIDataClass.Article
import com.example.newsapp.R

class NewsAdapter(private val newsList: List<Article>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var onClickListener: OnClickListener? = null
    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.titlehead)
        var img:ImageView=view.findViewById(R.id.img)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
            val news = newsList[position]
            holder.title.text = news.title

            Glide.with(holder.itemView.context)
                .load(news.urlToImage)
                .into(holder.img)

        holder.itemView.setOnClickListener {
            onClickListener?.onClick(position, news)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setOnClickListener(listener: OnClickListener?) {
        this.onClickListener = listener
    }

    interface OnClickListener {
        fun onClick(position: Int, item: Article)
    }
}
