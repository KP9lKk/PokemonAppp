package com.example.pokemonapp.ui.models

import com.example.pokemonapp.R
import com.example.pokemonapp.data.models.PokemonResponse

data class AboutScreenState(
    var color: Int = R.color.white,
    var pokemonResponse: PokemonResponse? = null
)