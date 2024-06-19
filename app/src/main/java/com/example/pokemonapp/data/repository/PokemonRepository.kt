package com.example.pokemonResponseapp.data.repository

import android.util.Log
import com.example.pokemonapp.data.models.PokemonResponse
import com.example.pokemonapp.data.network.PokemonServiceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRepository(val pokemonServiceImpl: PokemonServiceImpl) {
    fun getPokemons():Flow<List<PokemonResponse>>  = flow{
        val result = pokemonServiceImpl.getPokemons()
        val pokemonList = mutableListOf<PokemonResponse>()
        if(result.isSuccessful){
            val pokemonItems =  checkNotNull(result.body())
            pokemonItems.results.forEach {
                val pokemonResponse = pokemonServiceImpl.getPokemon(it.name)
                if(pokemonResponse.isSuccessful){
                    val pokemon = checkNotNull(pokemonResponse.body())
                    Log.d("CheckRepository", "getPokemons: ${pokemon.name}")
                    pokemonList.add(pokemon)
                }
            }
        }
        emit(pokemonList)
    }
}