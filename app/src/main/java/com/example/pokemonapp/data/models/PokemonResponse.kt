package com.example.pokemonapp.data.models

data class PokemonResponse(
    val id:Int,
    val name:String,
    val base_experience:Int,
    val height: Int,
    val is_default: Boolean,
    val order: Int,
    val weight: Int
)
