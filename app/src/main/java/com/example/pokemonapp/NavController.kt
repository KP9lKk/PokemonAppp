package com.example.pokemonapp

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.pokemonapp.ui.screens.AboutScreen

class NavController: FragmentManager() {

    fun navigateToAboutScreen(){
        commit {
            add<AboutScreen>(R.id.host_fragment)
        }
    }
}