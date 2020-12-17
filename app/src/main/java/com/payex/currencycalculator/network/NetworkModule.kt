package com.payex.currencycalculator.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Module
class NetworkModule @Inject constructor() {

    // @Inject lateinit
    var client: OkHttpClient = OkHttpClient()
    // I should have added support for parameter injection here
    val request = Request.Builder()
        .url("https://data.norges-bank.no/api/data/EXR/B..NOK.SP")
        .build()

    @Provides
    @Singleton
    fun fetchCurrencyValues() : String {
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            return response.body!!.string()
        }
    }
}

@Module
class NetworkUtil
{
    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient = OkHttpClient()
}