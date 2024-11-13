package com.wavelinemedia.cleanarchihilt.data.local

import javax.inject.Inject
import javax.inject.Singleton

interface LocalDataSource {
    suspend fun getAll(): List<LocalModel>

    suspend fun addAll(gyms: List<LocalModel>)

    suspend fun update(localGymFavouriteState: LocalModelFavouriteState)

    suspend fun getFavouriteList(): List<LocalModel>

    suspend fun updateAll(localModelFavouriteStates: List<LocalModelFavouriteState>)
}

@Singleton
class LocalDataSourceImpl @Inject constructor(
    private val waveLineMediaDao:
    WaveLineMediaDao,
) : LocalDataSource {
    override suspend fun getAll(): List<LocalModel> {
        return waveLineMediaDao.getAll()
    }

    override suspend fun addAll(gyms: List<LocalModel>) {
        waveLineMediaDao.addAll(gyms)
    }

    override suspend fun update(localGymFavouriteState: LocalModelFavouriteState) {
        waveLineMediaDao.update(localGymFavouriteState)
    }

    override suspend fun getFavouriteList(): List<LocalModel> {
        return waveLineMediaDao.getFavouriteGyms()
    }

    override suspend fun updateAll(localModelFavouriteStates: List<LocalModelFavouriteState>) {
        waveLineMediaDao.updateAll(localModelFavouriteStates)
    }

}