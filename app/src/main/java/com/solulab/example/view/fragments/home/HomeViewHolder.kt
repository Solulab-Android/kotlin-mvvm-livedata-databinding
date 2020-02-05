package com.solulab.example.view.fragments.home

import android.content.Context
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.solulab.example.R
import com.solulab.example.databinding.ItemHomeBinding


class HomeViewHolder(
    val binding: ItemHomeBinding,
    itemView: View,
    val mcontex: Context,
    val homeAdapter: HomeAdapter
) : RecyclerView.ViewHolder(binding.root) {

    lateinit var list: MutableList<HomeData>

    fun bind(data: HomeData, list: MutableList<HomeData>, homeViewHolder: HomeViewHolder) {
        binding.position = adapterPosition
        binding.holder = homeViewHolder
        binding.data = data
        this.list = list
    }

    fun onClicked(view: View, position: Int, data: HomeData) {
        val param = Pair<String, Any?>("homedata", data)
        view.findNavController()
            .navigate(R.id.action_fragmentHome_to_ImageDetailActivity, bundleOf(param))
    }


}
