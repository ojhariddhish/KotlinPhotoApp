package com.dnsoftindia.kotlinphotoapp.api

import com.dnsoftindia.kotlinphotoapp.models.PhotoList
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Ganesha on 5/25/17.
 */
class PhotoRetriever {

    private val service: PhotoAPI

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(PhotoAPI::class.java)
    }

    fun getPhotos(callBack: Callback<PhotoList>) {
        val call = service.getPhotos()
        call.enqueue(callBack)
    }

}