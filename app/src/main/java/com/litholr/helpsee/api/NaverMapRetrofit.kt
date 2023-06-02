package com.example.helpsee.api.navermaps

import com.example.helpsee.utils.Constants
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException


object NaverMapRetrofit {
    private var okHttpClient = OkHttpClient.Builder()
        .addInterceptor(NaverMapInterceptor())
        .build()

    val INSTANCE = Retrofit.Builder()
        .baseUrl(Constants.NaverAPIConstants.NAVER_MAPS_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .client(okHttpClient)
        .build()

    private class NaverMapInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Chain): Response {
            val modifiedRequest: Request = chain.request().newBuilder()
                .header(Constants.NaverAPIConstants.NAVER_HEADER_CLIENT_KEY_ID_KEY, Constants.NaverAPIConstants.NAVER_CLIENT_KEY_ID)
                .header(Constants.NaverAPIConstants.NAVER_HEADER_SECRET_CLIENT_KEY_KEY, Constants.NaverAPIConstants.NAVER_SECRET_CLIENT_KEY)
                .build()
            return chain.proceed(modifiedRequest)
        }
    }
}

