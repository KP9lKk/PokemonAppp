package com.example.pokemonapp.ui.models

import android.app.Application
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.PokemonApplication
import com.example.pokemonapp.R
import com.example.pokemonapp.data.models.PokemonResponse
import com.example.pokemonapp.ui.screens.AboutScreen
import com.example.pokemonapp.ui.screens.ChangeStateFragmentDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel(application: Application): AndroidViewModel(application) {

    private val repository by lazy { (application as PokemonApplication).repository }

    private val _stateMainScreen = MutableStateFlow(MainScreenState())
    val stateMainScreen = _stateMainScreen.asStateFlow()

    private val _stateAboutScreen = MutableStateFlow(AboutScreenState())
    val stateAboutScreen = _stateAboutScreen.asStateFlow()

    private var _fragmentManager: FragmentManager? = null

    fun selectPokemon(pokemonResponse: PokemonResponse){
        val pokemonSpecies = repository.getSpeciesByName(pokemonResponse.name)
        viewModelScope.launch {
            pokemonSpecies.collect{ resp ->
                _stateAboutScreen.update {
                    it.copy(
                        color = resp.color.name,
                        pokemonResponse = pokemonResponse) }
            }
        }
    }



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
    fun setFragmentManager(fragmentManager: FragmentManager){
        _fragmentManager = fragmentManager
    }
    fun getPokemons(){
        _stateMainScreen.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPokemons().collect{ result ->
                    _stateMainScreen.update {
                        it.copy(pokemonList = result, isLoading = false)
                    }
                }
            }
    }

    fun navigateToAbout(){
        _fragmentManager?.commit {
            replace<AboutScreen>(R.id.host_fragment)
            addToBackStack(null)
        }
    }

    fun showStateChangeDialog(childFragmentManager :FragmentManager){
        val dialog = ChangeStateFragmentDialog()
        dialog.show(childFragmentManager, "StateChangeDialog")
    }

}