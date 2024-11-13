package com.wavelinemedia.cleanarchihilt.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface WaveLineMediaDao {
    @Query("SELECT * FROM table_name")
    suspend fun getAll(): List<LocalModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(list: List<LocalModel>)

    @Update(entity = LocalModel::class)
    suspend fun update(localModelFavouriteState: LocalModelFavouriteState)

    @Query("SELECT * FROM table_name WHERE is_favorite = 1")
    suspend fun getFavouriteGyms(): List<LocalModel>

    @Update(entity = LocalModel::class)
    suspend fun updateAll(localModelFavouriteStates: List<LocalModelFavouriteState>)
}