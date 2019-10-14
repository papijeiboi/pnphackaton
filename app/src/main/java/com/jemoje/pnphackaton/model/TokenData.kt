package com.jemoje.pnphackaton.model

import com.google.gson.annotations.SerializedName

class TokenData{

    @SerializedName("token_type")
    var tokenType: String? = null

    @SerializedName("expires_in")
    var expiresIn: String? = null

    @SerializedName("access_token")
    var accessToken: String? = null

    @SerializedName("refresh_token")
    var refreshToken: String? = null
}