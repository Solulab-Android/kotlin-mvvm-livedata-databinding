package com.solulab.example.view.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.solulab.example.R
import com.solulab.example.databinding.ItemHomeBinding


class HomeAdapter(private val list: MutableList<HomeData>, val context: Context) :
    RecyclerView.Adapter<HomeViewHolder>() {


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binder = DataBindingUtil.inflate<ItemHomeBinding>(
            layoutInflater,
            R.layout.item_home,
            parent,
            false
        )
        val viewHolder = HomeViewHolder(binder, parent, context, this)

        return viewHolder
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(list[position], list)
    }


}