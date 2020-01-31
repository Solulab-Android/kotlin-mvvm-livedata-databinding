package com.solulab.example.network

import com.solulab.example.view.home.HomeData
import com.solulab.example.view.login.LoginData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {

    @GET("5e33d26e3000008300d96053")
    fun login(): Observable<BaseModel<List<LoginData>>>

    @GET("5e32799e320000301c94ce62")
    fun getHomeList(): Observable<BaseModel<List<HomeData>>>



}
