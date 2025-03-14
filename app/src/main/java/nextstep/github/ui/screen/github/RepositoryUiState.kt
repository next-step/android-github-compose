package nextstep.github.ui.screen.github

import nextstep.github.data.model.RepositoryModel

data class RepositoryUiState(
    val id: Int,
    val fullName: String,
    val description: String,
)

fun List<RepositoryModel>.toUiStateList() = map { it.toUiState() }

fun RepositoryModel.toUiState() = RepositoryUiState(
    id = id,
    fullName = fullName,
    description = description,
)
