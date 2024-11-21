package com.wavelinemedia.cookies.data.remote

import javax.inject.Inject
import javax.inject.Singleton

interface RemoteDataSource {
    suspend fun getList(): List<RemoteDTO>
    suspend fun getById(id: Int): Map<String, RemoteDTO>
}

@Singleton
class RemoteDataSourceImpl @Inject constructor(
    private val waveLineMediaApiService:
    WaveLineMediaApiService,
) : RemoteDataSource{
    override suspend fun getList(): List<RemoteDTO> {
        return waveLineMediaApiService.getList()
    }

    override suspend fun getById(id: Int): Map<String, RemoteDTO> {
        return waveLineMediaApiService.getById(id)

    }
}