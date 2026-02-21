package com.example.todolist.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.todolist.R
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding>(
    FragmentDetailBinding::inflate
) {
    private val viewModel: DetailViewModel by viewModels()

    override fun setupViews() {

    }

    override fun observeViewModel() {

    }

    override fun onClick(viewId: Int) {

    }


}