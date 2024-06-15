package com.example.pokemonapp.screens

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.CompoundButton.OnCheckedChangeListener
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar.LayoutParams
import androidx.fragment.app.DialogFragment
import com.example.pokemonapp.R

class ChangeStateFragmentDialog()
    : DialogFragment() {
   private var onCheckedChangeListener: RadioGroup.OnCheckedChangeListener?  = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.create()
        } ?: throw IllegalStateException()
        val view = layoutInflater.inflate(R.layout.fragment_change_state_dialog, null)
        dialog.setView(view)
        dialog.context.setTheme(R.style.StateAlertDialog)
        dialog.window?.attributes.let {
            it?.gravity = Gravity.END or Gravity.TOP
            it?.verticalMargin = 0.15f
        }
        val radioGroup = view.findViewById<RadioGroup>(R.id.ChangeStateGroup)
        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener)
        return dialog
    }

    fun setOnCheckedChangeListener(listener: RadioGroup.OnCheckedChangeListener){
        onCheckedChangeListener = listener
    }


}