package com.example.myapplication3.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val categoryId: Long,
    val amount: Double,
    val description: String,
    val date: Date,
    val startTime: Date,
    val endTime: Date,
    val photoPath: String? = null
) 