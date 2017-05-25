package com.dnsoftindia.kotlinphotoapp.api

import com.dnsoftindia.kotlinphotoapp.models.PhotoList
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Ganesha on 5/25/17.
 */
interface PhotoAPI {

    @GET("?key=YOUR_PIXABAY_KEY_HERE&q=nature&image_type=photo")
    fun getPhotos(): Call<PhotoList>

}