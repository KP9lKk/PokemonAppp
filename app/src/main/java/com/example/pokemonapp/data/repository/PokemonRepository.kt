package com.example.pokemonResponseapp.data.repository

import com.example.pokemonapp.data.models.PokemonItemsResponse
import com.example.pokemonapp.data.network.PokemonServiceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class PokemonRepository(val pokemonServiceImpl: PokemonServiceImpl) {
    fun getPokemons():Flow<Response<PokemonItemsResponse>>  = flow{
        emit(pokemonServiceImpl.getPokemons())
    }
}