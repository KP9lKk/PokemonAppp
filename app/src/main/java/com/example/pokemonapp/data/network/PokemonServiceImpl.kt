package com.example.pokemonapp.data.network

import com.example.pokemonapp.data.models.PokemonItemsResponse
import retrofit2.Response

class PokemonServiceImpl(private  val pokemonApi: PokemonApi) {
    suspend fun getPokemons(): Response<PokemonItemsResponse> = pokemonApi.getPokemons()
}