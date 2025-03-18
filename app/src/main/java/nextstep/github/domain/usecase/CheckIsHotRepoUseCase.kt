package nextstep.github.domain.usecase

class CheckIsHotRepoUseCase {

    operator fun invoke(stars: Int): Boolean {
        return stars >= 50
    }
}