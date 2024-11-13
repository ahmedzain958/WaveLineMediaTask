package com.wavelinemedia.cleanarchihilt.domain

import com.wavelinemedia.cleanarchihilt.data.remote.RemoteDTO

interface WaveLineMediaRepository {
    suspend fun toggleFavouriteModel(model: Int, favouriteState: Boolean)
    suspend fun getModels(): List<Model>
    suspend fun loadModels()
    suspend fun getModelById(id: Int): Map<String, RemoteDTO>
}