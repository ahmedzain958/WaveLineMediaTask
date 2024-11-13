package com.wavelinemedia.cleanarchihilt.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalModel::class], version = 1, exportSchema = false)
abstract class WaveLineMediaDatabase : RoomDatabase() {
    abstract val dao: WaveLineMediaDao


}