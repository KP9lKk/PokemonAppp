package com.example.pokemonapp.ui.screens

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentChangeStateDialogBinding
import com.example.pokemonapp.ui.models.MainScreenStateSort
import com.example.pokemonapp.ui.models.MainScreenViewModel
import kotlinx.coroutines.launch

class ChangeStateFragmentDialog() : DialogFragment() {
        private var changeStateBinding: FragmentChangeStateDialogBinding? = null
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.create()
        } ?: throw IllegalStateException()
            val viewModel by activityViewModels<MainScreenViewModel>()
        val view = layoutInflater.inflate(R.layout.fragment_change_state_dialog, null)
        dialog.setView(view)
            changeStateBinding = FragmentChangeStateDialogBinding.bind(view)
        dialog.context.setTheme(R.style.StateAlertDialog)
        dialog.window?.attributes.let {
            it?.gravity = Gravity.END or Gravity.TOP
            it?.verticalMargin = 0.15f
        }
            lifecycleScope.launch {
                viewModel.stateMainScreen.collect{
                    changeStateBinding?.NameCheck?.isChecked = it.isNameSorted
                    changeStateBinding?.NumberCheck?.isChecked = it.isNumberSorted
                }

            }

        val radioGroup = view.findViewById<RadioGroup>(R.id.ChangeStateGroup)
        radioGroup.setOnCheckedChangeListener{
                                             group, checkedId ->
            when(checkedId){
                R.id.NumberCheck -> {
                    viewModel.changeStateSort(MainScreenStateSort.NUMBER)
                }
                R.id.NameCheck -> {
                    viewModel.changeStateSort(MainScreenStateSort.NAME)
                }
            }
            dismiss()
        }
        return dialog
    }



}