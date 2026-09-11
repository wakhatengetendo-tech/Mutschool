package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_progress")
data class UserProgressEntity(
    @PrimaryKey val id: String,
    val type: String, // "HONOR", "CLASS_REQ", "BOOKMARK", "NOTE"
    val title: String,
    val category: String,
    val isCompleted: Boolean = false,
    val noteText: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
