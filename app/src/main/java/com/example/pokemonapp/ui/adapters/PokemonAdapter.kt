package com.example.pokemonapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.pokemonapp.R
import com.example.pokemonapp.data.models.PokemonResponse

class PokemonAdapter(private val pokemons: List<PokemonResponse>,
                     private val onClickCard: (PokemonResponse) -> Unit): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    class ViewHolder(view:View, private val onClick: (PokemonResponse) -> Unit): RecyclerView.ViewHolder(view) {

        val pokemonImage = view.findViewById<ImageView>(R.id.PokemonImage)
        val pokemonNumber = view.findViewById<TextView>(R.id.PokemonNumber)
        val pokemonName = view.findViewById<TextView>(R.id.PokemonName)
        val pokemonCard = view.findViewById<CardView>(R.id.PokemonCard)
        fun bind(pokemon: PokemonResponse){

            pokemonImage.load(pokemon.sprites.other.official_artwork.front_default){
                placeholder(R.drawable.shimmer_pokemon_image)
            }
            pokemonCard.setOnClickListener{
                onClick(pokemon)
            }
            pokemonNumber.text = pokemon.order.toString()
            pokemonName.text = pokemon.name.replaceFirstChar { it -> it.uppercase() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_items, parent, false )
        return ViewHolder(view, onClickCard)
    }

    override fun getItemCount(): Int = pokemons.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemons.get(position)
        holder.bind(pokemon)
    }
}
