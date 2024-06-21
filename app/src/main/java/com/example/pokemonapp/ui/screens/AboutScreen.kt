package com.example.pokemonapp.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.pokemonapp.R
import com.example.pokemonapp.data.models.PokemonResponse
import com.example.pokemonapp.data.models.TypeModel
import com.example.pokemonapp.databinding.FragmentAboutScreenBinding
import com.example.pokemonapp.ui.adapters.TypeAdapter
import com.example.pokemonapp.ui.models.MainScreenViewModel
import kotlinx.coroutines.launch


class AboutScreen : Fragment(R.layout.fragment_about_screen) {
    private var aboutScreenBinding : FragmentAboutScreenBinding? = null
    private val viewModel : MainScreenViewModel by activityViewModels()
    private val mapColor : MutableMap<String, Int> = mutableMapOf()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aboutScreenBinding = FragmentAboutScreenBinding.bind(view)

        initColor()

        lifecycleScope.launch {
            viewModel.stateAboutScreen.collect{state ->
                state.pokemonResponse?.let { pokemon -> setPokemon(pokemonResponse = pokemon) }
            }
        }
    }

    fun setPokemon(pokemonResponse: PokemonResponse){
        val typeAdapter = TypeAdapter(
            pokemonResponse.types.map {
                mapColor[it.type.name].let {color ->
                    TypeModel(color!!,it.type.name)
                }

        }
        )
        val color = mapColor[pokemonResponse.types[0].type.name]
        if (color != null) {
            setGlobalColor(color)
        }
        if(aboutScreenBinding  != null) {
            aboutScreenBinding!!.apply {
                TypeList.adapter = typeAdapter
                PokemonImageView.load(pokemonResponse.sprites.other.official_artwork.front_default)
                PokemonNameTitle.text = pokemonResponse.name
                PrimaryAbilityTextView.text = pokemonResponse.abilities[0].ability.name
                SecondaryAbilityTextView.text = pokemonResponse.abilities[1].ability.name
                PokemonNumberTitle.text = pokemonResponse.order.toString()
                WeightTextView.text = pokemonResponse.weight.toString()
                HeightTextView.text = pokemonResponse.height.toString()
            }
        }

    }

    fun setGlobalColor(color: Int){
        if(aboutScreenBinding != null){
            aboutScreenBinding?.MainLayout?.setBackgroundColor(color)
            aboutScreenBinding?.AboutTitle?.setTextColor(color)
            aboutScreenBinding?.BaseStatsTittle?.setTextColor(color)
        }
    }
    fun initColor(){
        mapColor.set("white",requireContext().getColor(R.color.white))
        mapColor.set("grass", requireContext().getColor(R.color.grass))
        mapColor.set("fire", requireContext().getColor(R.color.fire))
        mapColor.set("water", requireContext().getColor(R.color.water))
        mapColor.set("poison", requireContext().getColor(R.color.water))
    }
}