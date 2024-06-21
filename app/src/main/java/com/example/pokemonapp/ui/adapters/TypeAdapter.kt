package com.example.pokemonapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.data.models.TypeModel

class TypeAdapter(val typeList: List<TypeModel>) : RecyclerView.Adapter<TypeAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val card = view.findViewById<CardView>(R.id.TypeCard)
        val typeName = view.findViewById<TextView>(R.id.TypeTextView)

        fun bind(typeModel: TypeModel){
            card.setCardBackgroundColor(typeModel.color)
            typeName.text = typeModel.name

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_types, parent, false)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int = typeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(typeList[position])
    }
}