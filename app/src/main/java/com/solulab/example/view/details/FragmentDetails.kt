package com.solulab.example.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.solulab.example.BaseFragment
import com.solulab.example.R
import com.solulab.example.databinding.FragmentDetailsBinding



class FragmentDetails : BaseFragment() {
    private val viewModel by lazy { DetailsViewModel() }
    private lateinit var detailsBinding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details,
            container,
            false
        )
        return detailsBinding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /* if (savedInstanceState == null) viewModel.init(mContext)
        homeBinding.lifecycleOwner = this
        homeBinding.model = viewModel

        viewModel.getHomeList()*/

    }

}