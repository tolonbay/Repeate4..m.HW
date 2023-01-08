package com.example.repeate4mhw.presentation.ui.fragment.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.repeate4mhw.R
import com.example.repeate4mhw.base.BaseFragment
import com.example.repeate4mhw.databinding.FragmentBoardBinding
import com.example.repeate4mhw.presentation.App
import com.example.repeate4mhw.presentation.adapter.BoardAdapter
import com.google.android.material.tabs.TabLayoutMediator

class BoardFragment : BaseFragment<FragmentBoardBinding>(FragmentBoardBinding::inflate),BoardAdapter.OpenListener {

    private val adapter by lazy {
        BoardAdapter(this)
    }

    override fun setupUI() {
        binding.pager.adapter = adapter
        TabLayoutMediator(binding.tabs, binding.pager){ tab,_ -> tab.setIcon(R.drawable.tab_selector) }.attach()

        }

    override fun open() {
        controller.navigateUp()
        App.prefs.saveBoardState()
    }

}