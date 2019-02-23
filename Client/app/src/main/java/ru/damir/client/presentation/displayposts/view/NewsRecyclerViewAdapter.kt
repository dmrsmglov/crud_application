package ru.damir.client.presentation.displayposts.view

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.damir.client.R

import ru.damir.client.repository.model.Post

class NewsRecyclerViewAdapter : ListAdapter<Post, Holder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)
        holder.title.text = item.title
        holder.content.text = item.content
    }
}

class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title_text_view)
    val content: TextView = itemView.findViewById(R.id.content_text_view)
}

internal class DiffCallBack : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(p0: Post, p1: Post) = p0.id == p1.id

    override fun areContentsTheSame(p0: Post, p1: Post) = p0 == p1
}