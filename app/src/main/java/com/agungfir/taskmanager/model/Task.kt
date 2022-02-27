package com.agungfir.taskmanager.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Task(
    val id: Int? = null,
    val title: String? = null,
    var date: String? = null,
    var timestamp: Long? = null,
    val location: String? = null,
    val latitude: Long? = null,
    val longitude: Long? = null,
    val type: Int? = null,
    val taskColor: String? = null,
    val isComplete: Boolean? = false,
    val createdAt: Long? = null,
    val updatedAt: Long? = null
) : Parcelable