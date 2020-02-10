package com.solulab.example.view.fragments.upload

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.solulab.example.network.CallbackObserver
import com.solulab.example.network.Networking
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class UploadViewModel(mContext: Context) : ViewModel() {

    var uploadLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var body = ArrayList<MultipartBody.Part>()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun UploadImage(files: ArrayList<File>, name: String) {

        isLoading.postValue(true)

        for (file in files) {
            val requestFile: RequestBody =
                RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
            val multipart: MultipartBody.Part =
                MultipartBody.Part.createFormData("image[]", file.name, requestFile)
            body.add(multipart)
        }




        Networking
            .with()
            .getServices()
            .UploadData(body, name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<Boolean>() {
                override fun onSuccess(response: Boolean) {

                    Log.v("responseData", response.toString())
                    uploadLiveData.postValue(response)
                    isLoading.postValue(false)
                }

                override fun onFailed(code: Int, message: String) {
                    uploadLiveData.postValue(null)
                    isLoading.postValue(false)
                }
            })
    }

}
