package com.example.pokemonapp.ui.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.pokemonResponseapp.data.repository.PokemonRepository
import com.example.pokemonapp.PokemonApplication
import com.example.pokemonapp.data.models.PokemonItemResponse
import com.example.pokemonapp.ui.screens.MainScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel(private val repository: PokemonRepository): ViewModel() {
    private val _stateMainScreen = MutableStateFlow(MainScreenState())
    val stateMainScreen = _stateMainScreen.asStateFlow()

    fun changeStateSort(state: MainScreenStateSort){
        when(state){
            MainScreenStateSort.NAME -> {
                _stateMainScreen.update {
                    it.copy(isNameSorted = true, isNumberSorted = false)
                }
            }
            MainScreenStateSort.NUMBER -> {
                _stateMainScreen.update {
                    it.copy(isNameSorted = false, isNumberSorted = true)
                }
            }
        }
    }

    fun getPokemons(){
        _stateMainScreen.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository.getPokemons().collect{ result ->
                if (result.isSuccessful){
                    val pokemonItemsResponse = result.body()
                    Log.d("StateViewModel", pokemonItemsResponse?.count.toString())
                    _stateMainScreen.update {
                        it.copy(pokemonList = pokemonItemsResponse!!.results)
                    }
                }
                else{
                    _stateMainScreen.update {
                        it.copy(isLoading = false, isError = true)
                    }
                }
            }
        }
    }

    companion object {
        val Factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return MainScreenViewModel(
                    (application as PokemonApplication).repository)
                        as T
            }
        }
    }
}