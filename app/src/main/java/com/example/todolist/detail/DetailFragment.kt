package com.example.todolist.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding>(
    FragmentDetailBinding::inflate
) {

    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun setupViews() {

    }

    override fun observeViewModel() {

    }

    override fun onClick(viewId: Int) {

    }
}