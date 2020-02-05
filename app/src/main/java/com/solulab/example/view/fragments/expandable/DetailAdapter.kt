package com.solulab.example.view.fragments.expandable

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.solulab.example.R
import com.solulab.example.databinding.ItemDetailBinding

class DetailAdapter(
    private val list: MutableList<Hero>,
    val context: Context
) : RecyclerView.Adapter<DetailsViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binder = DataBindingUtil.inflate<ItemDetailBinding>(
            layoutInflater,
            R.layout.item_detail,
            parent,
            false
        )



        val viewHolder = DetailsViewHolder(binder, context, this)
        return viewHolder
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(list[position], list, holder)

    }


}