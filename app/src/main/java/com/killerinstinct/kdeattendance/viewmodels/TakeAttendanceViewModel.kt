package com.killerinstinct.kdeattendance.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killerinstinct.kdeattendance.models.Employee
import com.killerinstinct.kdeattendance.repository.MainRepository
import kotlinx.coroutines.launch

class TakeAttendanceViewModel(
    private val mainRepository: MainRepository
): ViewModel() {

    val employeeListLiveData = MutableLiveData<List<Employee>>()

    init {
        getAllEmployees()
    }

    private fun getAllEmployees() = viewModelScope.launch {
        employeeListLiveData.postValue(mainRepository.getAllEmployees())
    }
}