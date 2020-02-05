package com.solulab.example.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("setLinearVerticalAdapter")
fun bindRecyclerViewAdapter(
    recyclerView: RecyclerView,
    adapter: RecyclerView.Adapter<*>?
) {
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    recyclerView.adapter = adapter
}

@BindingAdapter("imageUrl")
fun loadImageGenericImage(view: ImageView, url: String) {
    Glide.with(view)
        .load(url)
        .apply(RequestOptions().centerCrop())
        .into(view)
}
