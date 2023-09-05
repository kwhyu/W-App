package com.example.w_app

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Waipu(
    val name: String,
    val description: String,
    val photo: String,
    val likes: String
) : Parcelable
