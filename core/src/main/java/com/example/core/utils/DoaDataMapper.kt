package com.example.core.utils

import com.example.core.data.local.DoaEntity
import com.example.core.data.network.response.DoaResponseItem
import com.example.core.domain.model.Doa

object DoaDataMapper {
    fun mapResponseToEntities(input: List<DoaResponseItem>): List<DoaEntity> {
        val doaList = ArrayList<DoaEntity>()
        input.map {
            val doa = DoaEntity(
                id = it.id,
                doa = it.doa,
                ayat = it.ayat,
                artinya = it.artinya,
                latin = it.latin,
                favorite = false
            )
            doaList.add(doa)
        }
        return doaList
    }

    fun mapEntitiesToDomain(input: List<DoaEntity>): List<Doa> =
        input.map {
            Doa(
                id = it.id,
                doa = it.doa,
                ayat = it.ayat,
                artinya = it.artinya,
                latin = it.latin,
                favorite = it.favorite
            )
        }

    fun mapDomainToEntity(input: Doa) = DoaEntity(
        id = input.id,
        doa = input.doa,
        ayat = input.ayat,
        artinya = input.artinya,
        latin = input.latin,
        favorite = input.favorite
    )
}