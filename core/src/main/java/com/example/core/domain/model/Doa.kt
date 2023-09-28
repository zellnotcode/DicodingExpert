package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Doa(
    val id: String,
    val doa: String,
    val ayat: String,
    val artinya: String,
    val latin: String,
    val favorite: Boolean
) : Parcelable