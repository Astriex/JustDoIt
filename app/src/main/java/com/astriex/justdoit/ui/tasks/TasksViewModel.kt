package com.astriex.justdoit.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.astriex.justdoit.data.PreferencesManager
import com.astriex.justdoit.data.SortOrder
import com.astriex.justdoit.data.Task
import com.astriex.justdoit.data.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskDao: TaskDao,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    val preferencesFlow = preferencesManager.preferencesFlow

    val searchQuery = MutableStateFlow("")

    private val taskFlow = combine(
        searchQuery,
        preferencesFlow
    ) { query, filterPreferences ->
        Pair(query, filterPreferences)
    }.flatMapLatest { (query, filterPreferences) ->
        taskDao.getTasks(query, filterPreferences.sortOrder, filterPreferences.hideCompleted)
    }

    val tasks = taskFlow.asLiveData()

    fun onSortOrderSelected(sortOrder: SortOrder) = viewModelScope.launch {
        preferencesManager.updateSortOrder(sortOrder)
    }

    fun onHideCompletedClick(hideCompleted: Boolean) = viewModelScope.launch {
        preferencesManager.updateHideCompleted(hideCompleted)
    }

    fun onTaskSelected(task: Task) {

    }

    fun onTaskCheckedChanged(task: Task, isChecked: Boolean) = viewModelScope.launch {
        // since properties in data class are immutable, we can only change the value of property by
        // making a copy of that object and updating the selected field
        taskDao.update(task.copy(completed = isChecked))
    }

}