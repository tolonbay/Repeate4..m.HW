package com.example.repeate4mhw.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.repeate4mhw.model.NoteModel

@Database(entities = [NoteModel::class], version = 1, exportSchema = false)
abstract class NoteDataBase: RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {

        @Volatile
        private var INSTANCE: NoteDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: buildDataBase(context).also {
                INSTANCE = it
            }
        }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NoteDataBase::class.java,
                "DB_NAME"
            ).allowMainThreadQueries().build()
    }
}