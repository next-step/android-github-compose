package nextstep.github.ui.screens.repositories.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.model.Repository
import nextstep.github.ui.theme.GithubTheme

@Composable
fun RepositoryItem(
    repository: Repository,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = repository.fullName,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = repository.description ?: "",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview(showBackground = true, name = "모든 값이 null이 아닐 때")
@Composable
private fun Preview1() {
    GithubTheme {
        RepositoryItem(
            repository = Repository(
                id = 1,
                fullName = "next-step/nextstep-docs",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소"),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true, name = "설명이 null일 때")
@Composable
private fun Preview2() {
    GithubTheme {
        RepositoryItem(
            repository = Repository(
                id = 1,
                fullName = "next-step/nextstep-docs",
                description = null
            ),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}


