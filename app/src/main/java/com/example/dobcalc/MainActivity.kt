package com.example.dobcalc

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnDatePicker : Button = findViewById(R.id.dateButton)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }

    }

    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val currentYear = myCalendar.get(Calendar.YEAR)
        val currentMonth = myCalendar.get(Calendar.MONTH) +1
        val currentDay = myCalendar.get(Calendar.DAY_OF_MONTH)
        val tvSelectedDate: TextView = findViewById(R.id.selectedDate)
        val tvAgeInMin:TextView = findViewById(R.id.tvAgeInMin)

        val dpd = DatePickerDialog(this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedMonthCorrected = selectedMonth + 1 //to correct from 0 indexing

                val selectedDate = "$selectedMonthCorrected/$selectedDayOfMonth/$selectedYear"
                tvSelectedDate.text = selectedDate

                val formattedDate  = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)

                val theDate = formattedDate.parse(selectedDate)
                val selectedDateInMinFrom1970 = theDate.time / 60000

                val currentDate = formattedDate.parse(formattedDate.format(System.currentTimeMillis()))
                val currentDateInMinFrom1970 = currentDate.time / 60000
//                Toast.makeText(this, "Current:$currentDateInMinFrom1970  , Selected:$selectedDateInMinFrom1970", Toast.LENGTH_SHORT).show()

                val differenceInMinutes = currentDateInMinFrom1970 - selectedDateInMinFrom1970
                tvAgeInMin.text = differenceInMinutes.toString()


            },
            currentYear, currentMonth, currentDay)
        dpd.datePicker.maxDate=System.currentTimeMillis() - 86400000
        dpd.show()
    }
}