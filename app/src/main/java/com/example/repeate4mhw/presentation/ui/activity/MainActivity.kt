package com.example.repeate4mhw.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.repeate4mhw.R
import com.example.repeate4mhw.databinding.ActivityMainBinding
import com.example.repeate4mhw.presentation.App

class MainActivity : AppCompatActivity() {

    private lateinit var controller: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavController()
    }

    private fun initNavController(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        controller= navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.noteFragment,
                R.id.contactFragment
            )
        )
        setupActionBarWithNavController(controller,appBarConfiguration)
        binding.bottomNav.setupWithNavController(controller)

        if (!App.prefs.isBoardShow()){
            controller.navigate(R.id.boardFragment)
        }
        controller.addOnDestinationChangedListener{ _, destination, _ ->
            val list: ArrayList<Int> = arrayListOf()
            list.add(R.id.noteFragment)
            list.add(R.id.contactFragment)
            if (list.contains(destination.id)){
                binding.bottomNav.isVisible = true
            }else{
                binding.bottomNav.isGone = true
            }
        }
    }
}