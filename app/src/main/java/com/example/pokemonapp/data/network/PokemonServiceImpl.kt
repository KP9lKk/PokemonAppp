package com.example.pokemonapp.data.network

import com.example.pokemonapp.data.models.PokemonItemsResponse
import com.example.pokemonapp.data.models.PokemonResponse
import retrofit2.Response

class PokemonServiceImpl(private  val pokemonApi: PokemonApi) {
    suspend fun getPokemons(): Response<PokemonItemsResponse> = pokemonApi.getPokemons()
    suspend fun getPokemon(name:String): Response<PokemonResponse> = pokemonApi.getPokemon(name)
}