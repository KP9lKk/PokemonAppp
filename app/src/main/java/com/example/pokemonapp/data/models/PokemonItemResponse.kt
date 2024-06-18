package com.example.pokemonapp.data.models

data class PokemonItemResponse(
    val name:String,
    val url: String
)
data  class PokemonItemsResponse (
    val count : Int,
    val next : String?,
    val previous: String?,
    val results : List<PokemonItemResponse>

)

