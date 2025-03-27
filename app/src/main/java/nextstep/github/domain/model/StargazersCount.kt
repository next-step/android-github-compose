package nextstep.github.domain.model

@JvmInline
value class StargazersCount(val value: Int) {

    init {
        require(value >= MIN_COUNT) { ERROR_OUT_OF_RANGE }
    }

    val isPopular: Boolean
        get() = value >= POPULAR_CONDITION

    companion object {
        private const val POPULAR_CONDITION = 50
        private const val ERROR_OUT_OF_RANGE = "Star 개수는 0 이상이어야 합니다."
        private const val MIN_COUNT = 0
    }
}
