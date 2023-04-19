package com.lttrung.zens.domain.locals.impl

import com.lttrung.zens.domain.locals.JokeLocals
import com.lttrung.zens.domain.locals.room.dao.JokeDao
import com.lttrung.zens.domain.locals.room.entities.Joke
import com.lttrung.zens.utils.Data
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class JokeLocalsImpl @Inject constructor(
    private val jokeDao: JokeDao
) : JokeLocals {
    override fun update(jokeId: Int, isFunny: Boolean): Single<Int> {
        return jokeDao.update(jokeId, isFunny)
    }

    override fun fetch(): Single<Joke> {
        return jokeDao.get()
    }

    override fun getAll(): Single<List<Joke>> {
        return jokeDao.getAll()
    }

    override fun insertSampleData() {
        val jokes = mutableListOf<Joke>()
        for (joke in Data.data) {
            jokes.add(joke)
        }
        jokeDao.insert(jokes)
    }
}