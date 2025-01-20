package com.example.expensetracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.Expense
import com.example.expensetracker.data.ExpenseDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val dao: ExpenseDao
) : ViewModel() {
    val expenses: LiveData<List<Expense>> = dao.getAllExpenses().asLiveData()

    fun addExpense(name: String, amount: Double, date: Long) {
        viewModelScope.launch {
            dao.insertExpense(Expense(name = name, amount = amount, date = date))
        }
    }
}
