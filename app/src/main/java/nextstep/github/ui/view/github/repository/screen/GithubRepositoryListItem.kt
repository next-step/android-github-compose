package nextstep.github.ui.view.github.repository.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.ui.composable.Star

@Composable
fun GithubRepositoryListItem(
    fullName: String,
    description: String,
    stars: Int,
    isHot: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(16.dp)) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isHot) {
                Text(
                    modifier = Modifier.align(Alignment.CenterStart),
                    text = "HOT",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Star(
                modifier = Modifier.align(Alignment.CenterEnd),
                count = stars
            )
        }
        Text(
            text = fullName,
            style = MaterialTheme.typography.titleLarge
        )
        if (description.isNotBlank()) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true, name = "Hot이 아닌 것")
@Composable
private fun GithubRepositoryListItemPreview1() {
    GithubRepositoryListItem(
        fullName = "next-step/nextstep-docs",
        description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
        stars = 0,
        isHot = false,
    )
}


@Preview(showBackground = true, name = "Hot 인 것")
@Composable
private fun GithubRepositoryListItemPreview2() {
    GithubRepositoryListItem(
        fullName = "next-step/nextstep-docs",
        description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
        stars = 123,
        isHot = true,
    )
}
