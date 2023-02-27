package com.example.internshala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.internshala.repository.Repository

class WorkshopViewModelFactory(private val repository: Repository):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WorkshopViewModel(repository) as T
    }
}