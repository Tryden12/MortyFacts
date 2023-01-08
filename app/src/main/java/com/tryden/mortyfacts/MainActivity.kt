package com.tryden.mortyfacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tryden.mortyfacts.Constants.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        val rickAppCompatActivity: RickyAndMortyService = retrofit.create(RickyAndMortyService::class.java)

        rickAppCompatActivity.getCharacterById().enqueue(object : Callback<GetCharacterByIdResponse> {
            override fun onResponse(call: Call<GetCharacterByIdResponse>, response: Response<GetCharacterByIdResponse>) {
                Log.e(TAG, response.toString() )

                if (!response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Unsuccessful network call!", Toast.LENGTH_SHORT).show()
                    return
                }

                val body = response.body()!!
                val name = body.name
                textView.text = name
            }

            override fun onFailure(call: Call<GetCharacterByIdResponse>, t: Throwable) {
                Log.e(TAG, t.message ?: "Null message" )
            }
        })
    }


}