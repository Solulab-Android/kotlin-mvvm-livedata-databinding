package com.solulab.example.view.activities.login

import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.solulab.example.network.BaseModel
import com.solulab.example.network.CallbackObserver
import com.solulab.example.network.Networking
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(val context: Context) : ViewModel() {
    var EmailAddress: ObservableField<String> = ObservableField()
    var Password: ObservableField<String> = ObservableField()

    var userMutableLiveData: MutableLiveData<LoginUser> = MutableLiveData()
    var loginMutableLiveData: MutableLiveData<LoginData> = MutableLiveData()

    fun getUser(): MutableLiveData<LoginUser> = userMutableLiveData

    fun getLogin() {
        Networking
            .with()
            .getServices()
            .login()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<List<LoginData>>>() {
                override fun onSuccess(response: BaseModel<List<LoginData>>) {
                    loginMutableLiveData.value = response.data?.get(0)
                }
                override fun onFailed(code: Int, message: String) {
                    Log.e("json-failed", message)
                    loginMutableLiveData.value = null
                }
            })

    }

    fun onClick() {
        val loginUser = LoginUser(EmailAddress.get(), Password.get())
        userMutableLiveData.value = loginUser
    }
}