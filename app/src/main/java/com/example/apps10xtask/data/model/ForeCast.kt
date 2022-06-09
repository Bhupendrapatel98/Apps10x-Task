package com.example.apps10xtask.data.model

data class ForeCast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForCasteList>,
    val message: Int
)