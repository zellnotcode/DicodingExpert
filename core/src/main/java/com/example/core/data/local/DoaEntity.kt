package com.example.core.data.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doa_data")
data class DoaEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String,
    @ColumnInfo(name = "doa")
    var doa: String,
    @ColumnInfo(name = "ayat")
    var ayat: String,
    @ColumnInfo(name = "artinya")
    var artinya: String,
    @ColumnInfo(name = "latin")
    var latin: String,
    @ColumnInfo(name = "favorite")
    var favorite: Boolean
)
