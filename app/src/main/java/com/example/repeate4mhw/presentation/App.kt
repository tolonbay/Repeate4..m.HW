package com.example.repeate4mhw.presentation

import android.app.Application
import android.content.SharedPreferences
import com.example.repeate4mhw.data.NoteDataBase
import com.example.repeate4mhw.presentation.utils.Prefs

class App : Application() {

    private lateinit var preferences: SharedPreferences

    companion object{
        lateinit var prefs:Prefs
        lateinit var db:NoteDataBase
    }

    override fun onCreate() {
        super.onCreate()
        preferences = this.applicationContext
            .getSharedPreferences("settings", MODE_PRIVATE )
        prefs = Prefs(preferences)
        db = NoteDataBase.invoke(this)
    }
}