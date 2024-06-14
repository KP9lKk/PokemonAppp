package com.example.pokemonapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.pokemonapp.R

class MainScreen : Fragment(R.layout.fragment_main_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view
            .findViewById<Button>(R.id.NavigateButton)

        button.setOnClickListener {
            parentFragmentManager.commit {
                replace<AboutScreen>(R.id.host_fragment)
            }
        }
    }
}