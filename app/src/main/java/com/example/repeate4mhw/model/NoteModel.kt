package com.example.repeate4mhw.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.IDN

@Entity
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String? = null,
    val des: String? = null,
    val date: String? = null
)
