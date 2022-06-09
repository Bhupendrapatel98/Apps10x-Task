package com.example.apps10xtask.utils

/**
 * Created by bhupendrapatel on 09/06/22.
 */
sealed class Resource<T> {

    class Loading<T> : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Failed<T>(val message: String) : Resource<T>()

    companion object {
        fun <T> loading() = Loading<T>()
        fun <T> success(data: T) = Success(data)
        fun <T> failed(message: String) = Failed<T>(message)
    }
}