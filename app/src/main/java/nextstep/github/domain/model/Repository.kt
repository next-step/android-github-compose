package nextstep.github.domain.model

import nextstep.github.data.model.dto.RepositoryResponse

data class Repository(
    val fullName: String,
    val description: String,
) {
    companion object {
        private const val DEFAULT_NAME = ""
        private const val DEFAULT_DESCRIPTION = ""

        fun fromResponse(response: RepositoryResponse) =
            Repository(
                fullName = response.fullName ?: DEFAULT_NAME,
                description = response.description ?: DEFAULT_DESCRIPTION
            )
    }
}


