package com.kotlin.andi.cinema.core.vo

data class Resource<T>(val status: Status, var data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> = Resource(Status.SUCCESS, data, null)

        fun <T> error(msg: String?, data: T?= null): Resource<T> = Resource(Status.ERROR, data, msg)

        fun <T> loading(data: T? = null): Resource<T> = Resource(Status.LOADING, data, null)
    }
}
