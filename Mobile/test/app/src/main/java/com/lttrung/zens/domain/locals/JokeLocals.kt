package com.lttrung.zens.domain.locals

import com.lttrung.zens.domain.locals.room.entities.Joke
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface JokeLocals {
    fun update(jokeId: Int, isFunny: Boolean): Single<Int>
    fun fetch(): Single<Joke>
    fun getAll(): Single<List<Joke>>
    fun insertSampleData()
}