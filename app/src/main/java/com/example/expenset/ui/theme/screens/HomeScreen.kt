package com.example.expensetracker.ui.screens

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.expensetracker.ExpenseViewModel
import com.example.expensetracker.data.Expense
import androidx.lifecycle.viewmodel.compose.viewModel
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.runtime.livedata.observeAsState



@Composable
fun HomeScreen(navController: NavController, viewModel: ExpenseViewModel = viewModel()) {
    val expenses by viewModel.expenses.observeAsState(emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = { navController.navigate("addExpense") }) {
            Text("Add Expense")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(expenses) { expense ->
                ExpenseItem(expense)
            }
        }
    }
}

@Composable
fun ExpenseItem(expense: Expense) {
    val formattedDate = remember(expense.date) {
        val dateFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
        dateFormat.format(Date(expense.date))
    }

    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "${expense.name}: $${"%.2f".format(expense.amount)}")
            Text(text = "Date: $formattedDate", style = MaterialTheme.typography.body2)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val fakeExpenses = listOf(
        Expense(name = "Lunch", amount = 12.5, date = System.currentTimeMillis()),
        Expense(name = "Groceries", amount = 55.0, date = System.currentTimeMillis() - 86400000L) // 1 day ago
    )

    // Pass the fake ViewModel to the HomeScreen composable for preview
    HomeScreen(navController = rememberNavController(), viewModel = FakeExpenseViewModel(fakeExpenses))
}
