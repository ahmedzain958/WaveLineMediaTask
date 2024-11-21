package com.wavelinemedia.cookies.data.remote

import com.google.gson.annotations.SerializedName

data class RemoteDTO(
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("location")
    val place: String,
    @SerializedName("is_open")
    val isOpen: Boolean
)
