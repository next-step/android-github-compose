package nextstep.github.ui.screens.list

import nextstep.github.model.Repository

data class RepositoryListUiState(
    val repositories: List<Repository> = emptyList(),
)
