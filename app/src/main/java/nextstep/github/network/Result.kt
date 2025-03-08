package nextstep.github.network

sealed class Result<out R> {
    fun onSuccess(func: (R) -> Unit): Result<R> {
        if (this is Success) {
            func.invoke(this.data)
        }
        return this
    }

    fun onError(func: (Throwable) -> Unit): Result<R> {
        if (this is Error) {
            func.invoke(this.exception)
        }
        return this
    }

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
}