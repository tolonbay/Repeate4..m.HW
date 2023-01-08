package com.example.repeate4mhw.data

import androidx.room.*
import com.example.repeate4mhw.model.NoteModel

@Dao
interface NoteDao {
    @Query("SELECT * FROM notemodel ORDER BY date ASC")
    fun getAllNoteBySortDate():List<NoteModel>

    @Insert
    fun addNote(model: NoteModel)

    @Delete
    fun deleteNote(model: NoteModel)

    @Update
    fun upDateNote(model: NoteModel)
}