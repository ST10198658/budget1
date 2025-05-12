package com.example.myapplication3.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: Expense)

    @Query("""
        SELECT * FROM expenses 
        WHERE userId = :userId 
        AND date BETWEEN :startDate AND :endDate
    """)
    fun getExpensesByDateRange(
        userId: Long,
        startDate: Date,
        endDate: Date
    ): Flow<List<Expense>>

    @Query("""
        SELECT SUM(amount) as total, categoryId 
        FROM expenses 
        WHERE userId = :userId 
        AND date BETWEEN :startDate AND :endDate
        GROUP BY categoryId
    """)
    fun getExpensesByCategory(
        userId: Long,
        startDate: Date,
        endDate: Date
    ): Flow<List<CategoryTotal>>

    data class CategoryTotal(
        val total: Double,
        val categoryId: Long
    )
} 