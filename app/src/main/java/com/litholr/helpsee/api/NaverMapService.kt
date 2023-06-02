package com.example.helpsee.api.navermaps

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverMapService {
    @GET("/dataversion")
    fun getDataVersion(): Call<String>

    @GET("/raster")
    fun getMapSampleImage(
        @Query("dataversion") dataversion: String,
        @Query("w") w: Int,
        @Query("h") h: Int,
        @Query("center") center: String,
        @Query("level") level: Int,
//        @Query("maptype") maptype: String?,
//        @Query("crs") crs: String?,
//        @Query("format") format: String?,
//        @Query("scale") scale: String?,
//        @Query("markers") markers: String?,
//        @Query("lang") lang: String?,
//        @Query("public_transit") publicTransit: String?
    ): Call<ResponseBody>
}