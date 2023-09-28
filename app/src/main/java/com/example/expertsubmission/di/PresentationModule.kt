package com.example.expertsubmission.di

import com.example.expertsubmission.ui.viewmodel.DetailViewModel
import com.example.expertsubmission.ui.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var presentationModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}