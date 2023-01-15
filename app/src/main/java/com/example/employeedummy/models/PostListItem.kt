package com.example.employeedummy.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostListItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
) : Parcelable