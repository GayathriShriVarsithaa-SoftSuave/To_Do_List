package com.example.todolist.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainer
import androidx.viewbinding.ViewBinding
import com.example.todolist.R
import com.example.todolist.listners.FragmentClickListener

abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean)->VB
): Fragment(), FragmentClickListener {
    protected lateinit var binding:VB private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState:Bundle?
    ):View {
        binding=bindingInflater(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModel()
    }
protected abstract fun setupViews()
protected abstract fun observeViewModel()
    abstract override fun onClick(viewId: Int)
}