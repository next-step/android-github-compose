package nextstep.github.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.model.NextStepRepositoryEntity
import nextstep.github.ui.theme.Purple50

@Composable
fun RepositoryItem(
    repository: NextStepRepositoryEntity
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (repository.isHot()) {
                HotText()
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "★ ${repository.stars ?: "No stars available"}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Text(
            text = repository.fullName ?: "No name available",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = repository.description ?: "No description available",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun HotText() {
    Text(
        text = "HOT",
        color = Purple50,
        style = MaterialTheme.typography.labelLarge
    )
}

@Preview(showBackground = true, name = "HOT 케이스")
@Composable
fun RepositoryItemHotPreview() {
    RepositoryItem(
        repository = NextStepRepositoryEntity(
            fullName = "nextstep/github",
            description = "Github 앱 구현을 위한 저장소",
            stars = 50
        )
    )
}

@Preview(showBackground = true, name = "HOT이 아닌 케이스")
@Composable
fun RepositoryItemNormalPreview() {
    RepositoryItem(
        repository = NextStepRepositoryEntity(
            fullName = "nextstep/github-compose",
            description = "Github Compose 앱 구현을 위한 저장소",
            stars = 49
        )
    )
}
