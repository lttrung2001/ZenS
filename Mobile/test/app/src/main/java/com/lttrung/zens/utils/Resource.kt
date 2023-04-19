package com.lttrung.zens.utils

open class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val t: Throwable) : Resource<T>()
    class Loading<T>(val data: T? = null) : Resource<T>()
}