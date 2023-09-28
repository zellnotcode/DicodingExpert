package com.example.expertsubmission.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.DoaUseCase
import com.example.core.domain.model.Doa
import kotlinx.coroutines.launch

class DetailViewModel constructor(private val useCase: DoaUseCase): ViewModel() {
    fun setFavoriteDoa(doa: Doa, state: Boolean) = viewModelScope.launch {
        useCase.setDoaToFavorite(doa, state)
    }
}