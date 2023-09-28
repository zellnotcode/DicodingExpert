package com.example.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.DoaUseCase

class FavoriteViewModel constructor(useCase: DoaUseCase): ViewModel() {
    val favoriteData = useCase.getFavoriteDoa().asLiveData()
}