package com.solulab.example

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    lateinit var mContext: Context
    lateinit var mActivity: Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mContext = context
        mActivity = context as Activity
    }

   /* fun setupToolbarWithMenu(view: View, title: String? = null, icon: Int = R.drawable.v_ic_menu) {
        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.toolbar))
        val actionBar = (activity as AppCompatActivity).supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false)
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(icon)
            if (title != null) tvTitle.text = title
        }
        toolbar.setNavigationOnClickListener {
            //(activity as MainActivity).openDrawer()
        }
    }*/
}
