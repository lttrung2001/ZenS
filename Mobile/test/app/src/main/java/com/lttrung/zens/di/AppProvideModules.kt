package com.lttrung.zens.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import com.lttrung.zens.domain.locals.room.JokeDatabase
import com.lttrung.zens.domain.locals.room.dao.JokeDao
import com.lttrung.zens.utils.JokeDatabaseUtils.JOKE_DB
import com.lttrung.zens.utils.JokeDatabaseUtils.JOKE_DB_VERSION
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppProvideModules {
    @Provides
    @Singleton
    fun provideJokeDatabase(@ApplicationContext context: Context): JokeDatabase {
        return Room.databaseBuilder(context, JokeDatabase::class.java, JOKE_DB).addMigrations(
            Migration(1, JOKE_DB_VERSION) {
                
            }
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideJokeDao(jokeDatabase: JokeDatabase): JokeDao {
        return jokeDatabase.jokeDao()
    }
}