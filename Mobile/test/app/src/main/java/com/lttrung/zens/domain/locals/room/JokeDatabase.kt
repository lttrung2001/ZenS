package com.lttrung.zens.domain.locals.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lttrung.zens.domain.locals.room.dao.JokeDao
import com.lttrung.zens.domain.locals.room.entities.Joke
import com.lttrung.zens.utils.JokeDatabaseUtils.JOKE_DB_VERSION

@Database(entities = [Joke::class], version = JOKE_DB_VERSION)
abstract class JokeDatabase : RoomDatabase() {
    abstract fun jokeDao(): JokeDao
}