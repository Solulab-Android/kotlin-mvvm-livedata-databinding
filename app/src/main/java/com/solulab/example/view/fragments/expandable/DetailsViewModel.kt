package com.solulab.example.view.fragments.expandable

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.solulab.example.network.BaseModel
import com.solulab.example.network.CallbackObserver
import com.solulab.example.network.Networking
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailsViewModel(val context: Context):ViewModel() {


    private val list: ArrayList<Hero> = ArrayList()
    private var detailLiveData: MutableLiveData<List<Hero>> = MutableLiveData()

    private lateinit var detailAdapter: DetailAdapter

    fun init() {
        detailAdapter = DetailAdapter(list,context )
        detailLiveData.observeForever {
            if (it != null) {
                list.clear()
                list.addAll(it)
                detailAdapter.notifyDataSetChanged()
            }
        }
        getDetailList()
    }

    fun getDetailAdapter(): DetailAdapter = detailAdapter

    fun getDetailList() {
        Networking
            .with()
            .getServices()
            .getDetailList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<List<Hero>>>() {
                override fun onSuccess(response: BaseModel<List<Hero>>) {
                    detailLiveData.postValue(response.data)
                }

                override fun onFailed(code: Int, message: String) {
                    detailLiveData.postValue(null)
                }
            })
    }
}
