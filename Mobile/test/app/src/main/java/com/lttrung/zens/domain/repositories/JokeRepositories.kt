package com.lttrung.zens.domain.repositories

import com.lttrung.zens.domain.locals.JokeLocals
import com.lttrung.zens.domain.locals.room.entities.Joke
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface JokeRepositories {
    val locals: JokeLocals
    fun update(jokeId: Int, isFunny: Boolean): Single<Int>
    fun fetch(): Single<Joke>
}