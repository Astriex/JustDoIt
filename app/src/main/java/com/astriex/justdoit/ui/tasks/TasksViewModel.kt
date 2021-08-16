package com.astriex.justdoit.ui.tasks

import androidx.lifecycle.ViewModel
import com.astriex.justdoit.data.TaskDao
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class TasksViewModel @Inject constructor(
    private val taskDao: TaskDao
) : ViewModel() {
}