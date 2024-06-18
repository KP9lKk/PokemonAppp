package com.example.pokemonapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.pokemonapp.R
import com.example.pokemonapp.data.models.PokemonItemResponse

class PokemonAdapter(private val pokemons: List<PokemonItemResponse>): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    class ViewHolder(view:View): RecyclerView.ViewHolder(view) {
        val pokemonImage = view.findViewById<ImageView>(R.id.PokemonImage)
        val pokemonNumber = view.findViewById<TextView>(R.id.PokemonNumber)
        val pokemonName = view.findViewById<TextView>(R.id.PokemonName)


        fun bind(pokemon: PokemonItemResponse){
            pokemonImage.setImageResource(R.drawable.shimmer_pokemon_image)
            pokemonNumber.text = "999"
            pokemonName.text = pokemon.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_items, parent, false )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = pokemons.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemons.get(position)
        holder.bind(pokemon)
    }
}
