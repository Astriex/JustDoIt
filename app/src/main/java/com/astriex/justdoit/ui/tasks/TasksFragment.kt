package com.astriex.justdoit.ui.tasks

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.astriex.justdoit.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksFragment : Fragment(R.layout.fragment_tasks) {
    private val viewModel by viewModels<TasksViewModel>()

}