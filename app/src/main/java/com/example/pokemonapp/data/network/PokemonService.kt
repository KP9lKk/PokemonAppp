package com.example.pokemonapp.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object PokemonService {
    val BASE_URL = "https://pokeapi.co/"
    private val interceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }
    private val okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(interceptor)
        .build()


    private fun createService() = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build()
    val pokemonService by lazy {
        createService().create(PokemonApi::class.java)
    }
}