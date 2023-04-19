package com.lttrung.zens.domain.usecases.impl

import com.lttrung.zens.domain.repositories.JokeRepositories
import com.lttrung.zens.domain.usecases.VoteJokeUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class VoteJokeUseCaseImpl @Inject constructor(
    private val jokeRepositories: JokeRepositories
) : VoteJokeUseCase {
    override fun execute(jokeId: Int, isFunny: Boolean): Single<Int> {
        return jokeRepositories.update(jokeId, isFunny)
    }
}