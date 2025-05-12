package com.example.myapplication3.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "budgets")
data class Budget(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val minMonthlyGoal: Double,
    val maxMonthlyGoal: Double
) 