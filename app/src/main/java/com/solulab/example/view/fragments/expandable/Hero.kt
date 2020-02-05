package com.solulab.example.view.fragments.expandable

import com.google.gson.annotations.SerializedName


class Hero() {
    @SerializedName("bio")
    var bio: String? = null
    @SerializedName("createdby")
    var createdby: String? = null
    @SerializedName("firstappearance")
    var firstappearance: String? = null
    @SerializedName("imageurl")
    var imageurl: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("publisher")
    var publisher: String? = null
    @SerializedName("realname")
    var realname: String? = null
    @SerializedName("team")
    var team: String? = null
}