package com.example.repeate4mhw.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.repeate4mhw.R
import com.example.repeate4mhw.databinding.ItemBoardBinding

class BoardAdapter(private val listener: OpenListener) : RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {

    val titleList = listOf("Замметки", "Кантакты", "Конец")
    val desList =
        listOf("Добавление заметок", "Доступ ко всем контактам", "Регистрация через email")
    val animList = listOf(
        R.raw.contact,
        R.raw.note,
        R.raw.notes

    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= BoardViewHolder (
        ItemBoardBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.onBind(position)
        holder.binding.btnItemStart.setOnClickListener {
            listener.open()
        }
    }

    override fun getItemCount() = titleList.size
    
   inner class BoardViewHolder (val binding: ItemBoardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int){
            binding.itemBoardAnim.setAnimation(animList[position])
            binding.itemBoardTitle.text = titleList[position]
            binding.itemBoardDes.text = desList[position]
            if (position ==titleList.size - 1){
                binding.btnItemStart.isVisible = true
            } else {
                binding.btnItemStart.isGone = true
            }
        }
    }
    interface OpenListener{
        fun open()
    }

}