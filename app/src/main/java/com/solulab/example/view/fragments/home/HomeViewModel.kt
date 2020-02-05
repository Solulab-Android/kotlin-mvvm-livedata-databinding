package com.solulab.example.view.fragments.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.solulab.example.network.BaseModel
import com.solulab.example.network.CallbackObserver
import com.solulab.example.network.Networking
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(val context: Context) : ViewModel() {

    private val videoList: ArrayList<HomeData> = ArrayList()
    private var homeLiveData: MutableLiveData<List<HomeData>> = MutableLiveData()

    private lateinit var homeAdapter: HomeAdapter
    private lateinit var homeViewHolder: HomeViewHolder

    fun init() {
        homeAdapter = HomeAdapter(videoList, context, this)
        homeLiveData.observeForever {
            if (it != null) {
                videoList.clear()
                videoList.addAll(it)
                homeAdapter.notifyDataSetChanged()
            }
        }
        getHomeList()
    }

    fun getHomeAdapter(): HomeAdapter = homeAdapter

    fun getHomeList() {
        Networking
            .with()
            .getServices()
            .getHomeList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<List<HomeData>>>() {
                override fun onSuccess(response: BaseModel<List<HomeData>>) {
                    homeLiveData.postValue(response.data)
                }

                override fun onFailed(code: Int, message: String) {
                    homeLiveData.postValue(null)
                }
            })
    }
}