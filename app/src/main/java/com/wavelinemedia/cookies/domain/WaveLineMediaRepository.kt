package com.wavelinemedia.cookies.domain

import com.wavelinemedia.cookies.data.remote.RemoteDTO

interface WaveLineMediaRepository {
    suspend fun toggleFavouriteModel(model: Int, favouriteState: Boolean)
    suspend fun getModels(): List<Model>
    suspend fun loadModels()
    suspend fun getModelById(id: Int): Map<String, RemoteDTO>
}