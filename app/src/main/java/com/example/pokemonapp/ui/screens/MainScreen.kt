package com.example.pokemonapp.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentMainScreenBinding
import com.example.pokemonapp.ui.adapters.PokemonAdapter
import com.example.pokemonapp.ui.models.MainScreenViewModel
import kotlinx.coroutines.launch

class MainScreen : Fragment(R.layout.fragment_main_screen) {

    private var mainScreenViewBinding: FragmentMainScreenBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: MainScreenViewModel by viewModels{MainScreenViewModel.Factory}
        mainScreenViewBinding = FragmentMainScreenBinding.bind(view)
        viewModel.getPokemons()
        lifecycleScope.launch {
            viewModel.  stateMainScreen.collect{
                if(it.isNumberSorted){
                    setSortByNumber(view)
                }
                if (it.isNameSorted){
                    setSortByName(view)
                }
                if (!it.pokemonList.isNullOrEmpty()){
                    val adapter  = PokemonAdapter(it.pokemonList)
                    mainScreenViewBinding?.PokemonList?.adapter = adapter
                }
            }
        }
        val dialog = ChangeStateFragmentDialog()
        mainScreenViewBinding?.ChangeMode?.setOnClickListener {
            dialog.show(childFragmentManager, "StateChange")
        }
    }


    fun setSortByName(view:View){
        val imageButton = view.findViewById<ImageButton>(R.id.ChangeMode)
        imageButton.setImageResource(R.drawable.ic_name)
    }
    fun setSortByNumber(view:View){
        val imageButton = view.findViewById<ImageButton>(R.id.ChangeMode)
        imageButton.setImageResource(R.drawable.ic_tag)
    }
}