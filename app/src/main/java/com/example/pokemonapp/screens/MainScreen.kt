package com.example.pokemonapp.screens

import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.ImageButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.annotation.StyleRes
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.pokemonapp.R
import com.google.android.material.card.MaterialCardView.OnCheckedChangeListener

class MainScreen : Fragment(R.layout.fragment_main_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val changeModeButton = view.findViewById<ImageButton>(R.id.ChangeMode)
        val dialog =
            ChangeStateFragmentDialog()
        val checkedChangeListener =
            RadioGroup.OnCheckedChangeListener {
                    group: RadioGroup?,
                    checkedId: Int ->
                when(checkedId){
                    R.id.NameCheck -> {
                    setSortByName(view)
                    }
                    R.id.NumberCheck -> {
                    setSortByNumber(view)
                    }

                }
                dialog.dismiss()
            }
        dialog.setOnCheckedChangeListener(checkedChangeListener)
        changeModeButton.setOnClickListener {
            dialog.show(
            parentFragmentManager,
            "ChangeStateDialog")
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