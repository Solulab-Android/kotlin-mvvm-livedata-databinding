package com.solulab.example.view.fragments.home

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


class HomeData() :Parcelable {

    @SerializedName("id")
    var id: String?=null
    @SerializedName("image")
    var image: String?=null
    @SerializedName("image_name")
    var image_name: String?=null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        image = parcel.readString()
        image_name = parcel.readString()
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(image)
        parcel.writeString(image_name)
    }
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HomeData> {
        override fun createFromParcel(parcel: Parcel): HomeData {
            return HomeData(parcel)
        }
        override fun newArray(size: Int): Array<HomeData?> {
            return arrayOfNulls(size)
        }
    }

}