package com.lttrung.zens.domain.usecases.impl

import com.lttrung.zens.domain.locals.room.entities.Joke
import com.lttrung.zens.domain.repositories.JokeRepositories
import com.lttrung.zens.domain.usecases.ViewJokeUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ViewJokeUseCaseImpl @Inject constructor(
    private val jokeRepositories: JokeRepositories
) : ViewJokeUseCase {
    override fun execute(): Single<Joke> {
        return jokeRepositories.fetch()
    }
}