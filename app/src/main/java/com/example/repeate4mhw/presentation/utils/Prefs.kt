package com.example.repeate4mhw.presentation.utils

import android.content.SharedPreferences
import java.util.prefs.AbstractPreferences

class Prefs(private val preferences: SharedPreferences) {
    fun saveBoardState(){
        preferences.edit().putBoolean("isShow",true).apply()
    }
    fun isBoardShow(): Boolean{
        return preferences.getBoolean("isShow",false)
    }
}