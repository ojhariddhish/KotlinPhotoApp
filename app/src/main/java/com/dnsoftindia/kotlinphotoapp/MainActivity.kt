package com.dnsoftindia.kotlinphotoapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.dnsoftindia.kotlinphotoapp.api.PhotoRetriever
import com.dnsoftindia.kotlinphotoapp.models.Photo
import com.dnsoftindia.kotlinphotoapp.models.PhotoList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var recyclerView: RecyclerView
    var photos: List<Photo>? = null
    var photoAdapter: PhotoAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("Photo App Home")

        recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        var retriever = PhotoRetriever()

        val callBack = object: Callback<PhotoList> {
            override fun onFailure(call: Call<PhotoList>?, t: Throwable?) {
                Log.e("MainActivity", "Error: " + t?.message)
            }

            override fun onResponse(call: Call<PhotoList>?, response: Response<PhotoList>?) {
                response?.isSuccessful.let {
                    this@MainActivity.photos = response?.body()?.hits
                    photoAdapter = PhotoAdapter(this@MainActivity.photos!!,
                            this@MainActivity)

                    recyclerView.adapter = photoAdapter
                }
            }

        }

        retriever.getPhotos(callBack)

    }

    override fun onClick(view: View?) {
        val intent = Intent(this, DetailActivity::class.java)
        val holder = view?.tag as PhotoAdapter.PhotoViewHolder

        intent.putExtra(DetailActivity.PHOTO, photoAdapter?.getPhoto(holder.adapterPosition))
        startActivity(intent)

    }


}
