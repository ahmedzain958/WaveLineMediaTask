package com.wavelinemedia.cleanarchihilt.data.remote

import retrofit2.http.GET
import retrofit2.http.Query


interface WaveLineMediaApiService {
    @GET("list.json")
    suspend fun getList(): List<RemoteDTO>

    @GET("list.json?orderBy=\"id\"")
    suspend fun getById(@Query("equalTo") id: Int): Map<String, RemoteDTO>
}