package com.example.pokemonapp
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.pokemonapp.ui.models.MainScreenViewModel
import com.example.pokemonapp.ui.screens.MainScreen

class MainActivity : AppCompatActivity() {
    val viewModel:MainScreenViewModel by viewModels()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.setFragmentManager(supportFragmentManager)
        supportFragmentManager.commit {
            add<MainScreen>(R.id.host_fragment)
        }

    }
}