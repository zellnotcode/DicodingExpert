package com.example.favorite.di

import com.example.favorite.viewmodel.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var FavoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}