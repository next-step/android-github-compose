package nextstep.github.network

sealed class Result<out R> {
    fun onSuccess(func: (R) -> Unit) {
        if (this is Success) {
            func.invoke(this.data)
        }
    }

    fun onError(func: (Throwable) -> Unit) {
        if (this is Error) {
            func.invoke(this.exception)
        }
    }

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
}