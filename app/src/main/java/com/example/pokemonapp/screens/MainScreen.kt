package com.example.pokemonapp.screens

import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.ImageButton
import androidx.annotation.StyleRes
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.pokemonapp.R

class MainScreen : Fragment(R.layout.fragment_main_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val changeModeButton = view.findViewById<ImageButton>(R.id.ChangeMode)
        changeModeButton.setOnClickListener {
            val menu = PopupMenu(requireContext(), it)
            val inflater: MenuInflater = menu.menuInflater
            inflater.inflate(R.menu.search_menu, menu.menu)
            menu.show()
        }
    }
}