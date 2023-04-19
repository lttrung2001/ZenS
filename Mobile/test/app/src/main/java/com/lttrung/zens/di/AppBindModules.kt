package com.lttrung.zens.di

import com.lttrung.zens.domain.locals.JokeLocals
import com.lttrung.zens.domain.locals.impl.JokeLocalsImpl
import com.lttrung.zens.domain.repositories.JokeRepositories
import com.lttrung.zens.domain.repositories.impl.JokeRepositoriesImpl
import com.lttrung.zens.domain.usecases.ViewJokeUseCase
import com.lttrung.zens.domain.usecases.VoteJokeUseCase
import com.lttrung.zens.domain.usecases.impl.ViewJokeUseCaseImpl
import com.lttrung.zens.domain.usecases.impl.VoteJokeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppBindModules {
    @Binds
    fun bindJokeLocals(impl: JokeLocalsImpl): JokeLocals

    @Binds
    fun bindJokeRepositories(impl: JokeRepositoriesImpl): JokeRepositories

    @Binds
    fun bindViewJokeUseCase(impl: ViewJokeUseCaseImpl): ViewJokeUseCase

    @Binds
    fun bindVoteJokeUseCase(impl: VoteJokeUseCaseImpl): VoteJokeUseCase
}