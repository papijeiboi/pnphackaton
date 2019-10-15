package com.jemoje.pnphackaton.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ScanStaffResponse {

    @SerializedName("id")
    var id: String? = null

    @SerializedName("user_id")
    var userId: String? = null

    @SerializedName("vehicle_id")
    var vehicleId: String? = null

    @SerializedName("processed_by")
    var processedBy: String? = null

    @SerializedName("qr_code")
    var qrCode: String? = null

    @SerializedName("scanned_at")
    var scannedAt: String? = null

    @SerializedName("completed_at")
    var completedAt: String? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("updated_at")
    var updatedAt: String? = null

    @SerializedName("clearance_description_id")
    var clearanceDescriptionId: String? = null

    @SerializedName("vehicle")
    @Expose
    var vehicle: VehicleData? = null

    @SerializedName("user")
    @Expose
    var user: UserData? = null

    @SerializedName("clearance")
    @Expose
    var clearance: ClearanceData? = null




//    "vehicle": {
//        "id": 1,
//        "user_id": 3,
//        "status": "payment on process",
//        "application_status": "online",
//        "crime_lab_inspection": "pending",
//        "hpg_inspection": "pending",
//        "findings": null,
//        "qr_code": "Paul-3-B31100-lq1BODHviB-2019-10-14-11:58:50",
//        "body_type": "Car",
//        "make": "Innova",
//        "year_model": "2019",
//        "color": "Red",
//        "engine_number": "12345678901234567890",
//        "chassis_number": "12345678901234567890",
//        "lto_cc_number": "4324343",
//        "plate_number": "B31100",
//        "or_number": "2342343",
//        "cr_number": "2424234",
//        "scanned_stencil_chassis": null,
//        "scanned_stencil_motor": null,
//        "scanned_stencil_chassis_url": null,
//        "scanned_stencil_motor_url": null,
//        "created_at": "2019-10-14 11:58:50",
//        "updated_at": "2019-10-14 11:58:50"
//    },
//    "user": {
//        "id": 3,
//        "qr_code": "Paul-123456789-QDRaya596e-2019-10-14-11:53:52",
//        "first_name": "Paul",
//        "last_name": "Marquita",
//        "middle_name": "Oblea",
//        "birthday": "1997-12-22",
//        "tin_number": "123456789",
//        "address": "Example Address",
//        "gender": "male",
//        "user_type": "applicant",
//        "email": "pamarquita@gmail.com",
//        "mobile_number": "09051476815",
//        "email_verified_at": null,
//        "avatar": null,
//        "avatar_url": null,
//        "created_at": "2019-10-14 11:53:52",
//        "updated_at": "2019-10-14 11:53:52"
//    },
//    "clearance": {
//        "id": 4,
//        "user_id": 3,
//        "vehicle_id": 1,
//        "processed_by": null,
//        "purpose": "New Registration",
//        "permit_to_assemble": "false",
//        "record_check": "false",
//        "land_bank_sbr_no": null,
//        "status": "payment on process",
//        "application_status": "walk_in",
//        "created_at": "2019-10-14 12:10:23",
//        "updated_at": "2019-10-14 12:10:23",
//        "requirements": null,
//        "requirements_url": null
//    }
}