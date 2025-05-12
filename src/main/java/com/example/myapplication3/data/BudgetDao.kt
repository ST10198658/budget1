package com.example.myapplication3.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBudget(budget: Budget)

    @Query("SELECT * FROM budgets WHERE userId = :userId")
    fun getBudgetByUser(userId: Long): Flow<Budget?>

    @Query("UPDATE budgets SET minMonthlyGoal = :minGoal, maxMonthlyGoal = :maxGoal WHERE userId = :userId")
    suspend fun updateBudgetGoals(userId: Long, minGoal: Double, maxGoal: Double)
} 