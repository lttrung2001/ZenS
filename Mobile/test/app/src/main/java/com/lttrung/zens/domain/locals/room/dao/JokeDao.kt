package com.lttrung.zens.domain.locals.room.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.OnConflictStrategy.REPLACE
import com.lttrung.zens.domain.locals.room.entities.Joke
import io.reactivex.rxjava3.core.Single

@Dao
interface JokeDao {
//    @Insert(onConflict = REPLACE)
//    fun insert(joke: Joke)

    @Insert(onConflict = ABORT)
    fun insert(jokes: List<Joke>)

//    @Update(onConflict = REPLACE)
//    fun update(joke: Joke): Single<Int>

//    @Delete
//    fun delete(joke: Joke): Single<Int>
//
//    @Query("SELECT * FROM Joke WHERE id = :jokeId")
//    fun get(jokeId: Int): Joke

    @Query("SELECT * FROM Joke WHERE isFunny = 0 LIMIT 1")
    fun get(): Single<Joke>

    @Query("SELECT * FROM Joke")
    fun getAll(): Single<List<Joke>>

    @Query("UPDATE Joke SET isFunny = :isFunny WHERE id = :jokeId")
    fun update(jokeId: Int, isFunny: Boolean): Single<Int>
}