package com.killerinstinct.kdeattendance.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killerinstinct.kdeattendance.repository.MainRepository
import com.killerinstinct.kdeattendance.models.Employee
import kotlinx.coroutines.launch

class MainViewModel(
    val mainRepository: MainRepository
): ViewModel() {

    fun addEmployee(employee: Employee) = viewModelScope.launch {
        mainRepository.addEmployee(employee)
    }

}