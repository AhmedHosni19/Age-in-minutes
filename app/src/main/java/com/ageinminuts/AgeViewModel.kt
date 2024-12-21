package com.ageinminuts

import android.icu.util.Calendar
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Locale

class AgeViewModel:ViewModel() {

    private val _selectedData=MutableLiveData<String>()
    val selectedData:LiveData<String> =_selectedData

    private val _ageInMinutes=MutableLiveData<String>()
    val ageInMinutes:LiveData<String> =_ageInMinutes

    fun onSelectedData(selectedYear: Int, selectedMonth: Int, selectedDay: Int){
        val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        _selectedData.value= selectedDate

        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

        val theDate = sdf.parse(selectedDate)


        if (theDate != null) {
            val selectedDateInMinutes = theDate.time / 60000

            val currentDate = Calendar.getInstance().time
            val currentDateInMinutes = currentDate.time / 60000

            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
            _ageInMinutes.value = differenceInMinutes.toString()

        } else {
            // Handle parsing error, e.g., show a toast or log an error
            Log.e("DateParsingError", "Failed to parse selected date")
        }
    }
}