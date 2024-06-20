package com.example.pokemonapp.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.pokemonapp.R
import com.example.pokemonapp.data.models.PokemonResponse
import com.example.pokemonapp.databinding.FragmentAboutScreenBinding
import com.example.pokemonapp.ui.models.MainScreenViewModel
import kotlinx.coroutines.launch


class AboutScreen : Fragment(R.layout.fragment_about_screen) {
    private var aboutScreenBinding : FragmentAboutScreenBinding? = null
    private val viewModel : MainScreenViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aboutScreenBinding = FragmentAboutScreenBinding.bind(view)

        lifecycleScope.launch {
            viewModel.stateAboutScreen.collect{state ->
                state.pokemonResponse?.let { pokemon -> setPokemon(pokemonResponse = pokemon) }
            }
        }
    }

    fun setPokemon(pokemonResponse: PokemonResponse){
        if(aboutScreenBinding  != null) {
            aboutScreenBinding!!.apply {
                PokemonNameTitle.text = pokemonResponse.name
                PokemonNumberTitle.text = pokemonResponse.order.toString()
                WeightTextView.text = pokemonResponse.weight.toString()
                HeightTextView.text = pokemonResponse.height.toString()
            }
        }
    }
}