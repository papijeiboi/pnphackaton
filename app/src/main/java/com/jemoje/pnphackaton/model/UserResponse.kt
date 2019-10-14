package com.jemoje.pnphackaton.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse {

    @SerializedName("token")
    @Expose
    var token: TokenData? = null

    @SerializedName("user")
    @Expose
    var user: UserData? = null
}