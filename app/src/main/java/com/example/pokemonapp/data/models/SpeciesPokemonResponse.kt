package com.example.pokemonapp.data.models

data class SpeciesPokemonResponse(
    val color: UrlResource,
    val flavor_text_entries: List<FlavorText>
)

data class FlavorText(
    val flavor_text: String,
    val language: UrlResource
)