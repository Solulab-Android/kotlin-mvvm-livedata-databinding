package com.solulab.example.network

import com.solulab.example.view.fragments.home.HomeData
import com.solulab.example.view.activities.login.LoginData
import com.solulab.example.view.fragments.expandable.Hero
import io.reactivex.Observable
import retrofit2.http.GET

interface APIInterface {

    @GET("5e33d26e3000008300d96053")
    fun login(): Observable<BaseModel<List<LoginData>>>

    @GET("5e32799e320000301c94ce62")
    fun getHomeList(): Observable<BaseModel<List<HomeData>>>


    @GET("5e3a52552f00009a3156c210")
    fun getDetailList():Observable<BaseModel<List<Hero>>>



}
