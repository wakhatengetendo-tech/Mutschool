package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.AppDatabase
import com.example.data.model.UserProgressEntity
import com.example.data.repository.AYRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AYViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    val repository = AYRepository(db.userProgressDao())

    val searchQuery = MutableStateFlow("")
    val selectedTab = MutableStateFlow(0) // 0: Hub, 1: Curriculum, 2: Honors & Knots, 3: Pledges & Songs, 4: Remnant Link Portal

    val userProgressList: StateFlow<List<UserProgressEntity>> = repository.allUserProgress
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val userProgressMap: StateFlow<Map<String, UserProgressEntity>> = userProgressList
        .map { list -> list.associateBy { it.id } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyMap()
        )

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
    }

    fun onTabSelected(index: Int) {
        selectedTab.value = index
    }

    fun toggleProgress(id: String, type: String, title: String, category: String, currentStatus: Boolean) {
        viewModelScope.launch {
            repository.toggleCompletion(id, type, title, category, currentStatus)
        }
    }

    fun saveNote(id: String, type: String, title: String, category: String, text: String) {
        viewModelScope.launch {
            repository.saveNote(id, type, title, category, text)
        }
    }

    fun deleteNote(id: String) {
        viewModelScope.launch {
            repository.deleteProgress(id)
        }
    }
}
