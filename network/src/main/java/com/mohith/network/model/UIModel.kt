package com.mohith.network.model

import com.google.gson.annotations.SerializedName

data class UIModel (
    @SerializedName("logo-url")
    val logoURL: String?,

    @SerializedName("heading-text")
    val headingText: String?,

    val uidata: List<UIData>?
)
