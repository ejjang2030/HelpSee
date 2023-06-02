package com.litholr.helpsee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.helpsee.api.navermaps.NaverMapRetrofit
import com.example.helpsee.api.navermaps.NaverMapService
import com.litholr.helpsee.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = NaverMapRetrofit.INSTANCE.create<NaverMapService>()

        retrofit.getDataVersion().enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful) {
                    val version = response.body() as String
                    Log.d("MainActivity", "version : $version")
                    binding.testText.text = version
                } else {
                    Log.d("MainActivity", "response not successful")
                }
                Log.d("MainActivity", "response.body() : " + response.body())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("MainActivity", "response failure")
            }
        })
    }
}