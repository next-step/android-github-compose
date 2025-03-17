package nextstep.github.domain.model

import nextstep.github.data.model.dto.RepositoryResponse

data class Repository(
    val id: Int,
    val fullName: String,
    val description: String,
    val stars: Int,
) {
    val isHot = stars >= MIN_HOT_STAR_COUNT

    companion object {
        private const val MIN_HOT_STAR_COUNT = 50

        private const val DEFAULT_DESCRIPTION = ""
        private const val DEFAULT_STAR_COUNT = 0

        fun fromResponse(response: RepositoryResponse) =
            Repository(
                id = response.id,
                fullName = response.fullName,
                description = response.description ?: DEFAULT_DESCRIPTION,
                stars = response.stars ?: DEFAULT_STAR_COUNT,
            )
    }
}


