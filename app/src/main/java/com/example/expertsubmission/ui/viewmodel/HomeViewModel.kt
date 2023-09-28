package com.example.expertsubmission.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.DoaUseCase

class HomeViewModel constructor(doaUseCase: DoaUseCase) : ViewModel() {
    val listDoa = doaUseCase.getAllDoa().asLiveData()
}