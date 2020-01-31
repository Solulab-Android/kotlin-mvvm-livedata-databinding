package com.solulab.example.view.home

import android.content.Context
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ui.PlayerView
import com.solulab.example.databinding.ItemHomeBinding


class HomeViewHolder(
    val binding: ItemHomeBinding,
    itemView: View,
    val mcontex: Context,
    val homeAdapter: HomeAdapter
) :
    RecyclerView.ViewHolder(binding.root){

    lateinit var list: MutableList<HomeData>



    fun bind(data: HomeData, list: MutableList<HomeData>) {
        binding.position = adapterPosition
        binding.holder = this
        binding.data = data
        this.list = list
    }


    fun showAllFeeds(view: View, position: Int, data: HomeData) {
        /*val intent = Intent(view.context, ActivityFeed::class.java)
        intent.putExtra("data", data.categoryName)
        view.context.startActivity(intent)*/

        val param = Pair<String, Any?>("title", data.image_name)
//        view.findNavController().navigate(R.id.action_fragmentHome_to_feedActivity, bundleOf(param))
    }



}
