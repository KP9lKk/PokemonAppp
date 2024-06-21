package com.example.pokemonapp.ui.models

import com.example.pokemonapp.data.models.PokemonResponse

data class AboutScreenState(
    var color: String = "white",
    var pokemonResponse: PokemonResponse? = null
)