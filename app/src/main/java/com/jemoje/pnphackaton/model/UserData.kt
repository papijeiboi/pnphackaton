package com.jemoje.pnphackaton.model

import com.google.gson.annotations.SerializedName

class UserData {

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("qr_code")
    var qrCode: String? = null

    @SerializedName("first_name")
    var firstName: String? = null

    @SerializedName("last_name")
    var lastName: String? = null

    @SerializedName("middle_name")
    var middleName: String? = null

    @SerializedName("birthday")
    var birthday: String? = null

    @SerializedName("classification")
    var classification: String? = null

    @SerializedName("tin_number")
    var tinNumber: String? = null

    @SerializedName("address")
    var address: String? = null

    @SerializedName("gender")
    var gender: String? = null

    @SerializedName("user_type")
    var userType: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("mobile_number")
    var mobileNumber: String? = null

    @SerializedName("avatar")
    var avatar: String? = null

    @SerializedName("email_verified_at")
    var emailVerifiedAt: String? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("updated_at")
    var updatedAt: String? = null
}