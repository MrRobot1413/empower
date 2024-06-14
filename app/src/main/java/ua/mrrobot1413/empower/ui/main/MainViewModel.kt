package ua.mrrobot1413.empower.ui.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.mrrobot1413.empower.data.DataProvider
import ua.mrrobot1413.empower.data.model.Beneficiary

// Could use DI
class MainViewModel(private val dataProvider: DataProvider): ViewModel() {

    private val _list = MutableLiveData<List<Beneficiary>>()
    val list: LiveData<List<Beneficiary>> = _list

    fun getBeneficiaries() {
        _list.value = dataProvider.getBeneficiaries()
    }
}