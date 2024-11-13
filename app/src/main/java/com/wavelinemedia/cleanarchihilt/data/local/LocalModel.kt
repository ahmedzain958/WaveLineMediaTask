package com.wavelinemedia.cleanarchihilt.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_name")
data class LocalModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "place")
    val place: String,
    @ColumnInfo(name = "is_open")
    val isOpen: Boolean,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false
)
