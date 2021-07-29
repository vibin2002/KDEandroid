package com.killerinstinct.kdeattendance.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killerinstinct.kdeattendance.repository.MainRepository
import com.killerinstinct.kdeattendance.models.Employee
import kotlinx.coroutines.launch

class MainViewModel(
    val mainRepository: MainRepository
): ViewModel() {

    val employeeListLiveData = MutableLiveData<List<Employee>>()

    init {
        getAllEmployees()
    }

    fun getAllEmployees() = viewModelScope.launch {
        employeeListLiveData.value = mainRepository.getAllEmployees()
    }

    fun addEmployee(employee: Employee) = viewModelScope.launch {
        mainRepository.addEmployee(employee)
    }.invokeOnCompletion {
        getAllEmployees()
    }

    fun deleteEmployee(id: Int) = viewModelScope.launch {
        mainRepository.deleteEmployee(id)
    }.invokeOnCompletion {
        getAllEmployees()
    }

}