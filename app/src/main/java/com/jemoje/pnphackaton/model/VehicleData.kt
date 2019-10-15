package com.jemoje.pnphackaton.model

import com.google.gson.annotations.SerializedName

class VehicleData {

    @SerializedName("id")
    var id: String? = null

    @SerializedName("user_id")
    var userId: String? = null

    @SerializedName("status")
    var status: String? = null

    @SerializedName("application_status")
    var applicationStatus: String? = null

    @SerializedName("crime_lab_inspection")
    var crimeLabInspection: String? = null

    @SerializedName("hpg_inspection")
    var hpgInspection: String? = null

    @SerializedName("findings")
    var findings: String? = null

    @SerializedName("qr_code")
    var qrCode: String? = null

    @SerializedName("body_type")
    var bodyType: String? = null

    @SerializedName("make")
    var make: String? = null

    @SerializedName("year_model")
    var yearModel: String? = null

    @SerializedName("color")
    var color: String? = null

    @SerializedName("engine_number")
    var engineNumber: String? = null

    @SerializedName("chassis_number")
    var chassisNumber: String? = null

    @SerializedName("lto_cc_number")
    var ltoCcNumber: String? = null

    @SerializedName("plate_number")
    var plateNumber: String? = null

    @SerializedName("or_number")
    var or_number: String? = null

    @SerializedName("cr_number")
    var cr_number: String? = null

    @SerializedName("scanned_stencil_chassis")
    var scanned_stencil_chassis: String? = null

    @SerializedName("scanned_stencil_motor")
    var scanned_stencil_motor: String? = null

    @SerializedName("scanned_stencil_chassis_url")
    var scanned_stencil_chassis_url: String? = null

    @SerializedName("scanned_stencil_motor_url")
    var scanned_stencil_motor_url: String? = null

    @SerializedName("created_at")
    var created_at: String? = null

    @SerializedName("updated_at")
    var updated_at: String? = null

}