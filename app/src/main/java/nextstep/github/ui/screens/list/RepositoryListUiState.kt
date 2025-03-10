package nextstep.github.ui.screens.list

import nextstep.github.model.RepositoryResponse

data class RepositoryListUiState(
    val repositories: List<RepositoryResponse> = emptyList(),
)
