package com.lttrung.zens.domain.locals.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Joke(
    @PrimaryKey val id: Int,
    val content: String,
    val isFunny: Boolean? = null
) : java.io.Serializable
