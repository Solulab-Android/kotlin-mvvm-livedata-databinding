package com.solulab.example.view.activities.ImageDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.solulab.example.R
import com.solulab.example.databinding.ActivityImageDetailBinding
import com.solulab.example.view.fragments.home.HomeData

class ImageDetailActivity : AppCompatActivity() {

    private var homeData: HomeData? = null
    lateinit var binding: ActivityImageDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_detail)
        homeData = intent.getParcelableExtra("homedata")
        binding.data = homeData
        binding.lifecycleOwner = this


    }
}
