package com.nelkinda.training

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.Date


class ExpenseReportTest{

    private val outputStreamCaptor: ByteArrayOutputStream = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @Test
    fun `should print expense name, amount and meal over expense marker as X`() {
        val expectedExpenseDate=Date()
        val expenseReport = ExpenseReport { expectedExpenseDate }
        val dinnerExpense = Expense()
        val amountOverDinnerExpense = 6000

        dinnerExpense.amount = amountOverDinnerExpense
        dinnerExpense.type = ExpenseType.DINNER

        val expenseList = listOf(dinnerExpense)
        expenseReport.printReport(expenseList)

        val expectedHeader = "Expenses $expectedExpenseDate\n"
        val expectedDinnerMarker = "Dinner\t$amountOverDinnerExpense\tX\n"
        val expectedMealExpense = "Meal expenses: $amountOverDinnerExpense\n"
        val expectedTotalExpense = "Total expenses: $amountOverDinnerExpense\n"
        val expectedOutput = expectedHeader + expectedDinnerMarker + expectedMealExpense + expectedTotalExpense
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString())

    }
}