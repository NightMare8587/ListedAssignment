package com.example.listedassignment.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listedassignment.Models.ListedModel
import com.example.listedassignment.NetworkCall.RetrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LinksViewModel : ViewModel() {
    var linksData = MutableLiveData<ListedModel>()

    fun getLinksData(token : String) {
        //fetching data and passing end point as parameter
       val retrofit = RetrofitObject(token).getInstance()
        retrofit?.getData("dashboardNew")?.enqueue(object : Callback<ListedModel> {
            override fun onResponse(call: Call<ListedModel>, response: Response<ListedModel>) {
                if(response.code() == 200) {
                    linksData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ListedModel>, t: Throwable) {

            }

        })
    }
}