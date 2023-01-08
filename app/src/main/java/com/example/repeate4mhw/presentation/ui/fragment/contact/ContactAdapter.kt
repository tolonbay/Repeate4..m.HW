package com.example.repeate4mhw.presentation.ui.fragment.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.repeate4mhw.databinding.ItemContactBinding
import com.example.repeate4mhw.model.ContactModel

public class ContactAdapter(private val listener : ClickListener,
                     private val contacts : ArrayList<ContactModel>)
    : Adapter<ContactAdapter.ContactViewHolder>() {

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
              ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    }

    fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.onBind(position)

        holder.itemView.setOnClickListener {
            if (holder.binding.contactsContainer.isGone) {
                holder.binding.contactsContainer.isVisible = true
            } else holder.binding.contactsContainer.isGone = true
        }

        holder.binding.btnCall.setOnClickListener {
            listener.callListener(position)
        }

        holder.binding.btnWhatsapp.setOnClickListener {
            listener.waListener(position)
        }
    }

    fun getItemCount() = contacts.size

    inner class ContactViewHolder(var binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            binding.tvItemContactName.text = contacts[position].name.toString()
            binding.tvItemContactNumber.text = contacts[position].number.toString()
        }


    }

    interface ClickListener {
        fun callListener(position: Int)
        fun waListener(position: Int)
    }
}