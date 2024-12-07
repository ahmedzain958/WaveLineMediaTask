package com.wavelinemedia.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Notification(
    var id: Int,
    var title: String,
    var timeInSeconds: Int
) : Parcelable
