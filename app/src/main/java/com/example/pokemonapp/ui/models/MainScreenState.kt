package com.example.pokemonapp.ui.models

data class MainScreenState(
    var isNumberSorted: Boolean = true,
    var isNameSorted: Boolean = false,
    val isLoading: Boolean = false,
    val searchText:String = "",
    val pokemonList: List<String> = emptyList()
)