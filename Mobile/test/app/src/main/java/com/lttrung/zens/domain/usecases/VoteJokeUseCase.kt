package com.lttrung.zens.domain.usecases

import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface VoteJokeUseCase {
    fun execute(jokeId: Int, isFunny: Boolean): Single<Int>
}