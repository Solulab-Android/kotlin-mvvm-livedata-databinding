package com.solulab.example.view.fragments.home
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.solulab.example.view.fragments.BaseFragment
import com.solulab.example.R
import com.solulab.example.databinding.FragmentHomeBinding


class FragmentHome : BaseFragment() {
    private val viewModel by lazy { HomeViewModel(this.mContext) }
    private lateinit var homeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        return homeBinding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) viewModel.init()
        homeBinding.lifecycleOwner = this
        homeBinding.model = viewModel

        viewModel.getHomeList()




    }

}