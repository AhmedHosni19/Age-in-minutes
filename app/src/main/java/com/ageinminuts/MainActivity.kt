package com.ageinminuts

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ageinminuts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private lateinit var binding:ActivityMainBinding
    private val viewModel:AgeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.selectDateBtn.setOnClickListener{
            showDatePicker()
        }

        viewModel.selectedData.observe(this){
            binding.birthdayDateTv.text=it
        }

        viewModel.ageInMinutes.observe(this){
            binding.ageInMinutesTv.text=it
        }
    }


    private fun showDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this@MainActivity,
        { _, selectedYear, selectedMonth, selectedDay ->
            viewModel.onSelectedData(selectedYear,selectedMonth,selectedDay)},
            year, month, day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}
