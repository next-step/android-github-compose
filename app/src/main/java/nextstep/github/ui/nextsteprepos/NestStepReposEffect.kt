package nextstep.github.ui.nextsteprepos

sealed interface NestStepReposEffect {
    data class ShowError(val message: String) : NestStepReposEffect
}
