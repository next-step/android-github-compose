package nextstep.github.domain.usecase

class CheckIsHotRepoUseCase {

    fun invoke(stars: Int): Boolean {
        return stars >= 50
    }
}