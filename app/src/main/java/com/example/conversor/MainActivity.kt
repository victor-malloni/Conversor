package com.example.conversor

import android.net.Network
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.conversor.API.EndPoint
import com.example.conversor.util.NetworkUtils
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCurrencies()
    }



    fun getCurrencies() {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://cdn.jsdelivr.net/")
        val endpoint = retrofitClient.create(EndPoint::class.java)

        endpoint.getCurrencies().enqueue(object : retrofit2.Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                var data = mutableListOf<String>()

                response.body()?.keySet()?.iterator()?.forEach {
                    data.add(it)
                }

                println(data.count())
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                println("Error callback")
            }
        })
    }
}