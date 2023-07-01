package com.example.listedassignment.NetworkCall

import com.example.listedassignment.Models.ListedModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LinksCall {

    @GET("{id}")
    fun getData(@Path("id") id : String?) : Call<ListedModel>
}