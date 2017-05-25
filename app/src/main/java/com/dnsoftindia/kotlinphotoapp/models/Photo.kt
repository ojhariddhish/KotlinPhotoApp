package com.dnsoftindia.kotlinphotoapp.models

import java.io.Serializable

/**
 * Created by Ganesha on 5/25/17.
 */
data class Photo(val id: String,
                 val likes: Int,
                 val favorites: Int,
                 val tags: String,
                 val previewURL: String,
                 val webformatURL: String) : Serializable {
}