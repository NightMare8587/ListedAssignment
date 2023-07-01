package com.example.listedassignment.NetworkCall

import com.example.listedassignment.Constants.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitObject(private val token: String) {
    var linksCall: LinksCall? = null

    @Synchronized
    fun getInstance(): LinksCall? {
        if (linksCall == null) {
            linksCall = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().addInterceptor { chain ->
                    //passing auth token in client so no need to pass this token in every retrofit function
                    val request = chain.request().newBuilder().addHeader("Authorization", "Bearer $token").build()
                    chain.proceed(request)
                }.build())
                .build()
                .create(LinksCall::class.java)
        }
        return linksCall
    }
}