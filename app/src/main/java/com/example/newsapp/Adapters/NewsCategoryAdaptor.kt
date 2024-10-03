package com.example.newsapp.Adapters

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R

class NewsCategoryAdaptor(private val category_item:List<String>, val idx:Int):RecyclerView.Adapter<NewsCategoryAdaptor.NewsViewsHolder>() {
    private var onClickListener: OnClickListener? = null
    private var selectedPosition: Int = idx
    class NewsViewsHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.category_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item_layout, parent, false)
        return NewsViewsHolder(view)
    }

    override fun getItemCount(): Int {
        return category_item.size
    }

    override fun onBindViewHolder(holder: NewsViewsHolder, position: Int) {
        holder.textView.text = category_item[position]

        if (position == selectedPosition) {
            holder.textView.setTypeface(null,Typeface.BOLD) // Make text bold
        } else {
            holder.textView.setTypeface(null,Typeface.NORMAL) // Set text to normal
        }

        holder.itemView.setOnClickListener {
            selectedPosition = holder.bindingAdapterPosition  // Set the clicked position
            notifyDataSetChanged()
            onClickListener?.onClick(position, category_item[position])
        }
    }

    fun setOnClickListener(listener: OnClickListener?) {
        this.onClickListener = listener
    }

    interface OnClickListener {
        fun onClick(position: Int, item: String)
    }
}