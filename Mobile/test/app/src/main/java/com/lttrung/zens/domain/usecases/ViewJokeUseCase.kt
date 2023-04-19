package com.lttrung.zens.domain.usecases

import com.lttrung.zens.domain.locals.room.entities.Joke
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface ViewJokeUseCase {
    fun execute(): Single<Joke>
}