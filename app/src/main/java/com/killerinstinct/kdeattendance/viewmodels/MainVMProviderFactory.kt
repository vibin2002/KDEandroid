package com.killerinstinct.kdeattendance.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.killerinstinct.kdeattendance.repository.MainRepository

class MainVMProviderFactory(
    private val mainRepository: MainRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(mainRepository) as T
    }
}