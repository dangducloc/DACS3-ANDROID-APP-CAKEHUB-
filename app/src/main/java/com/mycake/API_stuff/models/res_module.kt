package com.mycake.API_stuff.models

import com.google.gson.annotations.SerializedName

//login
data class login_result (
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("pass") val pass: String?,
    @SerializedName("email") val email: String?
)

//signup
data class signup_result (
    @SerializedName("signup") val signup: Boolean?
)

//list_cake
/*{
    "id": 1,
    "name": "Original Black Forest Cake",
    "price": 25,
    "left_Amount": 50,
    "info": "Original Black Forest Cake: A traditional German dessert made with layers of chocolate sponge cake, whipped cream, cherries, and chocolate shavings.",
    "Type": 1,
    "src": "1.jpg",
    "typename": "Eggless Cakes"
  } ,*/
data class cake(
    @SerializedName("id") val id:Int?,
    @SerializedName("name") val name:String?,
    @SerializedName("price") val price:Int?,
    @SerializedName("left_Amount") val left_Amount:Int?,
    @SerializedName("info") val info:String?,
    @SerializedName("Type") val Type:Int?,
    @SerializedName("src") val src:String?,
    @SerializedName("typename") val typename:String?,
)

//type
data class type(
    @SerializedName("id") val id:Int?,
    @SerializedName("Type") val Type:String?
)
/*{
    "userid": 1,
    "idCake": 12,
    "name": "Chocolate Delight",
    "price": 26,
    "Amount": 1,
    "src": "12.jpg"
  }*/
data class cart_item(
    @SerializedName("userid") val userid:Int?,
    @SerializedName("idCake") val idCake:Int?,
    @SerializedName("name") val name:String?,
    @SerializedName("price") val price:Int?,
    @SerializedName("Amount") val Amount:Int?,
    @SerializedName("src") val src:String?
)

data class updateCart_item(
    @SerializedName("updated_itemCart") val updated_itemCart:Boolean?
)

data class  delete_CartItem(
    @SerializedName("deleted_item") val delete_item:Boolean?
)

data class  add_CartItem(
    @SerializedName("addCarted") val addCarted:Boolean?
)

data class paymentMethod(
    @SerializedName("id") val id:Int?,
    @SerializedName("method") var method:String?
)

data class checkOut(
    @SerializedName("checkedout") val checkedout: Boolean?,
    @SerializedName("wrongCake") val wrongCake: Int?
)