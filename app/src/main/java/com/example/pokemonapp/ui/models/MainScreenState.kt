package com.example.pokemonapp.ui.models

import com.example.pokemonapp.data.models.PokemonItemResponse

data class MainScreenState(
    var isNumberSorted: Boolean = true,
    var isNameSorted: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val searchText:String = "",
    var pokemonList: List<PokemonItemResponse> = emptyList()
)