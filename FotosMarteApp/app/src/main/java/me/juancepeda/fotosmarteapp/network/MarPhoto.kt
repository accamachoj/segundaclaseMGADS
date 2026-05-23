package me.juancepeda.fotosmarteapp.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarPhoto(
    val id: String,
    @SerialName("img_src") val imgSrc: String
)