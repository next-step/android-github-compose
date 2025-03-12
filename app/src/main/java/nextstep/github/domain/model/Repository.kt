package nextstep.github.domain.model

import nextstep.github.data.model.dto.RepositoryResponse

data class Repository(
    val id: Int,
    val fullName: String,
    val description: String,
) {
    companion object {
        private const val DEFAULT_DESCRIPTION = ""

        fun fromResponse(response: RepositoryResponse) =
            Repository(
                id = response.id,
                fullName = response.fullName,
                description = response.description ?: DEFAULT_DESCRIPTION
            )
    }
}


