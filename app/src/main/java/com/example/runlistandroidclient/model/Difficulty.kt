package com.example.runlistandroidclient.model

import com.google.gson.annotations.SerializedName

enum class Difficulty(val value: Int) {
    @SerializedName("1")
    Easy(1),
    @SerializedName("2")
    Medium(2),
    @SerializedName("3")
    Hard(3)
}