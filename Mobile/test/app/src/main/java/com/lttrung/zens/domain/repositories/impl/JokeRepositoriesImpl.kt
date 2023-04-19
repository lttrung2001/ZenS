package com.lttrung.zens.domain.repositories.impl

import com.lttrung.zens.domain.locals.JokeLocals
import com.lttrung.zens.domain.locals.room.entities.Joke
import com.lttrung.zens.domain.repositories.JokeRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class JokeRepositoriesImpl @Inject constructor(override val locals: JokeLocals) : JokeRepositories {
    override fun update(jokeId: Int, isFunny: Boolean): Single<Int> {
        return locals.update(jokeId, isFunny)
    }

    override fun fetch(): Single<Joke> {
        return locals.fetch()
    }
}