package com.fahmy.news.data.dto

import com.google.gson.annotations.SerializedName

data class SourceDto(

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,
)