package com.example.repeate4mhw.presentation.ui.fragment.addnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.repeate4mhw.R
import com.example.repeate4mhw.base.BaseFragment
import com.example.repeate4mhw.databinding.FragmentAddNoteBinding
import com.example.repeate4mhw.model.NoteModel
import com.example.repeate4mhw.presentation.App
import java.text.SimpleDateFormat
import java.util.*

class AddNoteFragment : BaseFragment<FragmentAddNoteBinding>(FragmentAddNoteBinding::inflate) {

    override fun setupUI() {
        binding.btnSaveNote.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val des = binding.edtDescription.text.toString()
            val date = getCurrentDate()
            val dateInString = date.toString("yyy/MM/dd HH:mm:ss")

            App.db?.getNoteDao()?.addNote(
                NoteModel(title = title, des = des, date = dateInString)
            )
        }
    }
}
    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()):String{
        val formatter = SimpleDateFormat(format,locale)
    return formatter.format(this)
    }

    private fun getCurrentDate(): Date{
        return Calendar.getInstance().time
    }