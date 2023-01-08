package com.example.repeate4mhw.presentation.ui.fragment.note

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.repeate4mhw.R
import com.example.repeate4mhw.base.BaseFragment
import com.example.repeate4mhw.databinding.FragmentNoteBinding
import com.example.repeate4mhw.model.NoteModel
import com.example.repeate4mhw.presentation.App
import com.example.repeate4mhw.presentation.adapter.NoteAdapter

class NoteFragment : BaseFragment<FragmentNoteBinding>(FragmentNoteBinding::inflate) {

   private val adapter by lazy {
       NoteAdapter()
   }
       override fun setupUI() {
        binding.rvNote.adapter = adapter
        adapter.setList(App.db.getNoteDao().getAllNoteBySortDate() as ArrayList<NoteModel>)
    }

    override fun setupObserver() {
        deleteNote()
    }
    private fun deleteNote(){
        val simpleCallback = object :
        ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                AlertDialog.Builder(requireContext())
                    .setTitle("Удалит заметку")
                    .setNegativeButton("Нет"){_: DialogInterface?, _: Int->
                        adapter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                    .setPositiveButton("Да"){_:DialogInterface?, _: Int->
                        adapter.deleteNote((viewHolder.adapterPosition))
                    }
                    .show()
            }

        }
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvNote)
    }

    override fun initListeners() {
        binding.btnAddNote.setOnClickListener{
            controller.navigate(R.id.addNoteFragment)
        }
    }
}