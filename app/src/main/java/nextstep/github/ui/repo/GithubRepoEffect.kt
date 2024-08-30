package nextstep.github.ui.repo

sealed interface GithubRepoEffect {
    data class ShowErrorMessage(val message: String) : GithubRepoEffect
}
