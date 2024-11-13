package com.wavelinemedia.cleanarchihilt.data

import com.wavelinemedia.cleanarchihilt.data.local.LocalDataSource
import com.wavelinemedia.cleanarchihilt.data.local.LocalModel
import com.wavelinemedia.cleanarchihilt.data.local.LocalModelFavouriteState
import com.wavelinemedia.cleanarchihilt.data.remote.RemoteDataSource
import com.wavelinemedia.cleanarchihilt.domain.Model
import com.wavelinemedia.cleanarchihilt.domain.WaveLineMediaRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WaveLineMediaRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val dispatcher: CoroutineDispatcher,
) : WaveLineMediaRepository {
    override suspend fun toggleFavouriteModel(model: Int, favouriteState: Boolean) {
        withContext(dispatcher) {
            localDataSource.update(LocalModelFavouriteState(model, favouriteState))
            return@withContext localDataSource.getAll()
        }
    }

    override suspend fun getModels(): List<Model> =
        withContext(dispatcher) {
            return@withContext localDataSource.getAll().map {
                Model(it.id, it.name, it.place, it.isOpen, it.isFavorite)
            }
        }

    private suspend fun updateLocalDatabase() {
        val list = remoteDataSource.getList()
        val favouriteModelsList = localDataSource.getFavouriteList()
        localDataSource.addAll(list.map {
            LocalModel(it.id, it.name, it.place, it.isOpen)
        })
        localDataSource.updateAll(favouriteModelsList.map {
            LocalModelFavouriteState(it.id, true)
        })
    }

    override suspend fun loadModels() {
        try {
            updateLocalDatabase()
        } catch (e: Exception) {
            if (localDataSource.getAll().isEmpty())
                throw Exception("Something went wrong. try connecting to internet")
        }
        localDataSource.getAll()
    }

    override suspend fun getModelById(id: Int) = withContext(dispatcher) {
        remoteDataSource.getById(id)
    }
}