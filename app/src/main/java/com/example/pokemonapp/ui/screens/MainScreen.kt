package com.example.pokemonapp.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentMainScreenBinding
import com.example.pokemonapp.ui.models.MainScreenViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainScreen : Fragment(R.layout.fragment_main_screen) {

    private var mainScreenViewBinding: FragmentMainScreenBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel by viewModels<MainScreenViewModel>()
        mainScreenViewBinding = FragmentMainScreenBinding.bind(view)
        lifecycleScope.launch {
            viewModel.stateMainScreen.collect{
                if(it.isNumberSorted){
                    setSortByNumber(view)
                }
                if (it.isNameSorted){
                    setSortByName(view)
                }
            }
        }
        val dialog = ChangeStateFragmentDialog()
        mainScreenViewBinding?.ChangeMode?.setOnClickListener {
            dialog.show(childFragmentManager, "ChangeState")
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