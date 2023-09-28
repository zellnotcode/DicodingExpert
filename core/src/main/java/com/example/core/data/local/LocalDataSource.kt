package com.example.core.data.local

import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val doaDao: DoaDao) {

    fun getAllDoa() : Flow<List<DoaEntity>> = doaDao.getAllDoa()

    fun getFavoriteDoa() : Flow<List<DoaEntity>> = doaDao.getFavoriteDoa()

    fun insertFavorite(listDoa: List<DoaEntity>) = doaDao.insertDoa(listDoa)

    fun setFavoriteDoa(doa: DoaEntity, newState: Boolean) {
        doa.favorite = newState
        doaDao.updateFavoriteDoa(doa)
    }
}