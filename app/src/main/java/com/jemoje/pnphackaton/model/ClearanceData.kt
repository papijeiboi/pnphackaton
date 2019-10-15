package com.jemoje.pnphackaton.model

import com.google.gson.annotations.SerializedName

class ClearanceData {

    @SerializedName("id")
    var id: String? = null

    @SerializedName("user_id")
    var user_id: String? = null

    @SerializedName("vehicle_id")
    var vehicle_id: String? = null

    @SerializedName("processed_by")
    var processed_by: String? = null

    @SerializedName("purpose")
    var purpose: String? = null

    @SerializedName("permit_to_assemble")
    var permit_to_assemble: String? = null

    @SerializedName("record_check")
    var record_check: String? = null

    @SerializedName("land_bank_sbr_no")
    var land_bank_sbr_no: String? = null

    @SerializedName("status")
    var status: String? = null

    @SerializedName("application_status")
    var application_status: String? = null

    @SerializedName("created_at")
    var created_at: String? = null

    @SerializedName("updated_at")
    var updated_at: String? = null

    @SerializedName("requirements")
    var requirements: String? = null

    @SerializedName("requirements_url")
    var requirements_url: String? = null
}