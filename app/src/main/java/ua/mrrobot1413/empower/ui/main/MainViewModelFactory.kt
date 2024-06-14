package ua.mrrobot1413.empower.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.mrrobot1413.empower.data.DataProvider

class MainViewModelFactory(private val dataProvider: DataProvider) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(dataProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}