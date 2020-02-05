package com.solulab.example.view.fragments.expandable

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.solulab.example.R
import com.solulab.example.databinding.ItemDetailBinding

class DetailsViewHolder(
    val binding: ItemDetailBinding,
    val mcontex: Context,
    val detailAdapter: DetailAdapter
) : RecyclerView.ViewHolder(binding.root) {

    lateinit var list: MutableList<Hero>

    fun bind(data: Hero, list: MutableList<Hero>, detailViewHolder: DetailsViewHolder) {
        binding.position = adapterPosition
        binding.holder = detailViewHolder
        binding.data = data
        this.list = list
        binding.linearLayout.visibility = View.GONE


        var linearLayout: LinearLayout = binding.linearLayout
        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {
            //creating an animation
            val slideDown = AnimationUtils.loadAnimation(mcontex, R.anim.slide_down)

            //toggling visibility
            binding.linearLayout.visibility = View.VISIBLE
            //adding sliding effect
            binding.linearLayout.startAnimation(slideDown)
        }

        binding.textViewName

    }


    companion object {

        private var currentPosition = 0
    }


    fun onClicked(view: View, position: Int) {
        currentPosition = position
        //reloding the list
        detailAdapter.notifyDataSetChanged()
    }


}
