package com.pemmob.nalendra.aplikasidigimon.data.model

import com.google.gson.annotations.SerializedName

// Data class untuk response List (Daftar Digimon)
data class DigimonListResponse(
    @SerializedName("content")
    val content: List<DigimonListItem>?
)

// Data class untuk item di dalam daftar Digimon
data class DigimonListItem(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("image")
    val image: String?
)

// Data class untuk response Detail Digimon
data class DigimonDetailResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("images")
    val images: List<DigimonImage>?,
    @SerializedName("levels")
    val levels: List<DigimonLevel>?,
    @SerializedName("types")
    val types: List<DigimonType>?,
    @SerializedName("attributes")
    val attributes: List<DigimonAttribute>?
)

data class DigimonImage(
    @SerializedName("href")
    val href: String?
)

data class DigimonLevel(
    @SerializedName("level")
    val level: String?
)

data class DigimonType(
    @SerializedName("type")
    val type: String?
)

data class DigimonAttribute(
    @SerializedName("attribute")
    val attribute: String?
)
