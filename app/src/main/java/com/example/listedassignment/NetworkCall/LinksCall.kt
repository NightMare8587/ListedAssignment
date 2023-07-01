package com.example.listedassignment.NetworkCall

import com.example.listedassignment.Models.ListedModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LinksCall {
    //passing endpoint as parameter not hardcoded
    //so we can create multiple end points with single function
    @GET("{id}")
    fun getData(@Path("id") id : String?) : Call<ListedModel>
}