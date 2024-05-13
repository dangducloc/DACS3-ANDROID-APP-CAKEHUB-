package com.mycake.API_stuff.retrofit_and_more

import android.util.Log
import com.mycake.API_stuff.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class API_Manager {
    private val retrofit = ServiceBuilder.buildService(RestAPI::class.java)
    fun host():String{
        return ServiceBuilder.BaseHost
    }

    fun login(login: login_req, onResult: (login_result?) -> Unit){
        retrofit.login(login).enqueue(
            object : Callback<login_result> {

                override fun onFailure(call: Call<login_result>, t: Throwable) {
                    onResult(null)
                    Log.e("err", "Lỗi dmm", t)
                }

                override fun onResponse(
                    call: Call<login_result>,
                    response: Response<login_result>
                ) {
                    val loged = response.body()
                    onResult(loged)
                    Log.d("Login_testing", loged.toString())
                }
            }
        )

    }

    fun signup(signup: sign_up_req, onResult: (signup_result?) -> Unit){
        retrofit.signup(signup).enqueue(
            object : Callback<signup_result> {

                override fun onFailure(call: Call<signup_result>, t: Throwable) {
                    onResult(null)
                    Log.e("err1", "Lỗi dmm", t)
                }

                override fun onResponse(
                    call: Call<signup_result>,
                    response: Response<signup_result>
                ) {
                    val signed = response.body()
                    onResult(signed)
                    Log.d("signUp_testing", signed.toString())
                }
            }
        )
    }

    fun getcakes(onResult: (ArrayList<cake>?) -> Unit) {
        retrofit.getcakes().enqueue(
            object : Callback<ArrayList<cake>> {

                override fun onFailure(call: Call<ArrayList<cake>>, t: Throwable) {
                    onResult(null)
                    Log.e("err2", "Failed to fetch cakes", t)
                }

                override fun onResponse(
                    call: Call<ArrayList<cake>>,
                    response: Response<ArrayList<cake>>
                ) {
                    val cakes = response.body()
                    onResult(cakes)
                    Log.d("getCakes_testing", cakes.toString())
                }
            }
        )
    }

    fun gettypes(onResult: (ArrayList<type>?) -> Unit) {
        retrofit.gettypes().enqueue(
            object : Callback<ArrayList<type>> {

                override fun onFailure(call: Call<ArrayList<type>>, t: Throwable) {
                    onResult(null)
                    Log.e("err3", "Failed to fetch types", t)
                }

                override fun onResponse(
                    call: Call<ArrayList<type>>,
                    response: Response<ArrayList<type>>
                ) {
                    val types = response.body()
                    onResult(types)
                    Log.d("getTypes_testing", types.toString())
                }
            }
        )
    }

    fun getCart(getCartReq: get_cart_req, onResult: (ArrayList<cart_item>?) -> Unit){
        retrofit.getCart(getCartReq).enqueue(
            object : Callback<ArrayList<cart_item>> {
                override fun onFailure(
                    call: Call<ArrayList<cart_item>>,
                    t: Throwable
                ) {
                    onResult(null)
                    Log.e("err4", "Lỗi dmm", t)
                }

                override fun onResponse(
                    call: Call<ArrayList<cart_item>>,
                    response: Response<ArrayList<cart_item>>
                ) {
                    val cart = response.body()
                    onResult(cart)
                    Log.d("getcart_testing", cart.toString())
                }
            }
        )
    }

    fun upDate_cartItem(req: update_cartItem_req, onResult: (updateCart_item?) -> Unit){
        retrofit.update_CartItem(req).enqueue(
            object : Callback<updateCart_item> {

                override fun onFailure(
                    call: Call<updateCart_item>,
                    t: Throwable
                ) {
                    onResult(null)
                    Log.e("err5", "Lỗi dmm", t)
                }

                override fun onResponse(
                    call: Call<updateCart_item>,
                    response: Response<updateCart_item>
                ) {
                    val rs = response.body()
                    onResult(rs)
                    Log.d("updateCart_testing", rs.toString())
                }
            }
        )

    }

    fun delete_CartItem(req:delete_CartItem_req,onResult: (delete_CartItem?)->Unit){
        retrofit.delete_CartItem(req).enqueue(
            object : Callback<delete_CartItem>{
                override fun onFailure(
                    call: Call<delete_CartItem>,
                    t: Throwable) {
                    onResult(null)
                    Log.e("err6", "Lỗi dmm", t)
                }

                override fun onResponse(
                    call: Call<delete_CartItem>,
                    response: Response<delete_CartItem>) {
                    val rs  = response.body()
                    onResult(rs)
                    Log.d("delete_item_testing",rs.toString())
                }
            }
        )
    }

    fun add_CartItem(req:add_CartItem_req,onResult: (add_CartItem?) -> Unit){
        retrofit.add_CartItem(req).enqueue(
            object : Callback<add_CartItem>{
                override fun onResponse(
                    call: Call<add_CartItem>,
                    response: Response<add_CartItem>
                ) {
                    val rs = response.body()
                    onResult(rs)
                    Log.d("add_item_testing",rs.toString())
                }

                override fun onFailure(
                    call: Call<add_CartItem>,
                    t: Throwable) {
                    onResult(null)
                    Log.e("err7", "Lỗi dmm", t)
                }

            }
        )
    }

    fun get_paymentMetods(onResult:( ArrayList<paymentMethod>?) -> Unit){
        retrofit.get_paymentMetods().enqueue(
            object : Callback<ArrayList<paymentMethod>>{
                override fun onResponse(
                    call: Call<ArrayList<paymentMethod>>,
                    response: Response<ArrayList<paymentMethod>>
                ) {
                    val rs = response.body()
                    onResult(rs)
                    Log.d("add_item_testing",rs.toString())
                }

                override fun onFailure(
                    call: Call<ArrayList<paymentMethod>>,
                    t: Throwable) {
                    onResult(null)
                    Log.e("err8", "Lỗi dmm", t)
                }

            }
        )
    }

    fun CheckOut(req:checkout_req ,onResult: (checkOut?) -> Unit){
        retrofit.checkOut(req = req).enqueue(
            object : Callback<checkOut>{
                override fun onResponse(
                    call: Call<checkOut>,
                    response: Response<checkOut>) {
                    val rs = response.body()
                    onResult(rs)
                    Log.d("checking_out_testing",rs.toString())
                }

                override fun onFailure(
                    call: Call<checkOut>,
                    t: Throwable) {
                    onResult(null)
                    Log.e("err9", "Lỗi dmm", t)
                }

            }
        )
    }

}




