package com.mycake.API_stuff.models

import com.google.gson.annotations.SerializedName

data class login_req (
    @SerializedName("name") val name:String?,
    @SerializedName("pass") val pass:String?
)

data class sign_up_req(
    @SerializedName("name") val name:String?,
    @SerializedName("email") val mail:String?,
    @SerializedName("pass") val pass:String?,
    @SerializedName("pass2") val pass2:String?
)

data class get_cart_req(
    @SerializedName("iduser") val iduser:Int?
)

data class update_cartItem_req(
    @SerializedName("iduser") val iduser:Int?,
    @SerializedName("idcake") val idcake:Int?,
    @SerializedName("amount") val amount:Int?
)

data class delete_CartItem_req(
    @SerializedName("iduser") val iduser:Int?,
    @SerializedName("idcake") val idcake:Int?
)

data class add_CartItem_req(
    @SerializedName("iduser") val iduser:Int?,
    @SerializedName("idcake") val idcake:Int?,
    @SerializedName("amount") val amount:Int?
)

data class  checkout_req(
    @SerializedName("iduser") val iduser:Int?,
    @SerializedName("address") val address:String?,
    @SerializedName("payment") val payment:Int?,
)
