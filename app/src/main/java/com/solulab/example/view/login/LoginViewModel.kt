package com.solulab.example.view.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.solulab.example.BaseActivity
import com.solulab.example.network.BaseModel
import com.solulab.example.network.CallbackObserver
import com.solulab.example.network.Networking
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel : ViewModel() {
    var EmailAddress: MutableLiveData<String?>? = MutableLiveData()
    var Password: MutableLiveData<String?>? = MutableLiveData()
    var userMutableLiveData: MutableLiveData<LoginUser?>? = null
    var loginMutableLiveData:MutableLiveData<LoginData>?=null
    lateinit var mcontext: Context
    var success: MutableLiveData<String> = MutableLiveData()


    fun init(context: Context) {
        mcontext = context
        if (loginMutableLiveData == null) {
            loginMutableLiveData = MutableLiveData()
        }
        loginMutableLiveData!!.observeForever {
            if (it != null) {
                success.value = it.success

            }
            else{
                success.value="Failed"
            }
        }

    }

    fun getUser(): MutableLiveData<LoginUser?>? {
        if (userMutableLiveData == null) {
            userMutableLiveData = MutableLiveData()
        }
        return userMutableLiveData
    }


    fun getLogin() {
        Networking
            .with()
            .getServices()
            .login()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<List<LoginData>>>() {
                override fun onSuccess(response: BaseModel<List<LoginData>>) {
                    loginMutableLiveData!!.value = response.data?.get(0)
                }
                override fun onFailed(code: Int, message: String) {
                    Log.e("json-failed", message)
                    loginMutableLiveData!!.value = null
                }
            })

    }

    fun onClick() {
        val loginUser = LoginUser(EmailAddress!!.getValue(), Password!!.getValue())
        userMutableLiveData!!.setValue(loginUser)
    }
}