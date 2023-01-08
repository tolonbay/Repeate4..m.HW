package com.example.repeate4mhw.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.repeate4mhw.databinding.FragmentAddNoteBinding
import com.example.repeate4mhw.databinding.ItemNoteBinding
import com.example.repeate4mhw.model.NoteModel
import com.example.repeate4mhw.presentation.App

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var list = arrayListOf<NoteModel>()

    fun setList(List:ArrayList<NoteModel>){
        this.list = list
        notifyDataSetChanged()
    }
    fun deleteNote(position: Int){
        App.db?.getNoteDao()?.deleteNote(list.removeAt(position))
        notifyItemRemoved(position)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
     NoteViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
    holder.onBind(list[position])
    }

    override fun getItemCount() = list.size
    class NoteViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root) {

        fun onBind(noteModel: NoteModel){
            binding.itemTvTitle.text = noteModel.title
            binding.tvDes.text = noteModel.des
            binding.itemTvData.text = noteModel.date
        }
    }
}