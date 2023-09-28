package com.example.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DoaDao {

    @Query("SELECT * FROM doa_data")
    fun getAllDoa() : Flow<List<DoaEntity>>

    @Query("SELECT * FROM doa_data WHERE favorite = 1")
    fun getFavoriteDoa() : Flow<List<DoaEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDoa(doa: List<DoaEntity>)

    @Update
    fun updateFavoriteDoa(doa: DoaEntity)
}