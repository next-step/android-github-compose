package nextstep.github.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.github.ui.model.GithubRepositoryModel

class GithubRepositoryParameterProvider : PreviewParameterProvider<GithubRepositoryModel> {
    override val values: Sequence<GithubRepositoryModel>
        get() = sequenceOf(
            hasNameHasDescription,
            hasNameNoDescription,
            noNameHasDescription,
            noNameNoDescription,
            longNameHasDescription,
            hasNameLongDescription,
        )
}

private val hasNameHasDescription = GithubRepositoryModel(
    id = 0,
    fullName = "next-step/nextstep-docs",
    description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
)
private val hasNameNoDescription = GithubRepositoryModel(
    id = 1,
    fullName = "next-step/nextstep-docs",
    description = null,
)
private val noNameHasDescription = GithubRepositoryModel(
    id = 2,
    fullName = null,
    description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
)
private val noNameNoDescription = GithubRepositoryModel(
    id = 3,
    fullName = null,
    description = null,
)
private val longNameHasDescription = GithubRepositoryModel(
    id = 4,
    fullName = "next-step/nextstep-docs 일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십",
    description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
)
private val hasNameLongDescription = GithubRepositoryModel(
    id = 5,
    fullName = "next-step/nextstep-docs",
    description = "nextstep 매뉴얼 및 문서를 관리하는 저장소 일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십"
)
