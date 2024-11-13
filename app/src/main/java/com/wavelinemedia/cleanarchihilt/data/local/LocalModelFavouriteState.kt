package com.wavelinemedia.cleanarchihilt.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "table_name")
data class LocalModelFavouriteState(
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false
)
