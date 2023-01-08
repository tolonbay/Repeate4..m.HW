package com.example.repeate4mhw.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.example.repeate4mhw.R

typealias Inflate<T> = (LayoutInflater,ViewGroup?,Boolean) ->T

abstract class BaseFragment<VB: ViewBinding>(
    private val inflate : Inflate<VB>
    ):Fragment() {
     private var _binding: VB? = null
     protected val binding get() = _binding!!

    private var _controller: NavController? = null
    protected val controller get() = _controller!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater,container,false)
        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        _controller = navHostFragment.navController
        setupUI()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    setupObserver()
    initListeners()
    }

    abstract fun setupUI()
    open fun setupObserver(){}
    open fun initListeners(){}

    }
