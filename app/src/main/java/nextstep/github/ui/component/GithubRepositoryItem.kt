package nextstep.github.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.R
import nextstep.github.ui.theme.GithubTheme

@Composable
fun GithubRepositoryItem(
    fullName: String,
    description: String,
    startCount: Int,
    isHot: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (isHot) {
                Text(
                    text = stringResource(R.string.repository_hot_item),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge
                )
            }
            Spacer(Modifier.weight(1f))
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = stringResource(R.string.repository_start_count, fullName, startCount),
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = startCount.toString(),
                style = MaterialTheme.typography.labelLarge
            )
        }
        Text(
            text = fullName,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true, name = "star 50개 미만 Repository Preview")
@Composable
private fun Preview1() {
    GithubTheme {
        GithubRepositoryItem(
            fullName = "next-step/nextstep-docs",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
            startCount = 30,
            isHot = false
        )
    }
}

@Preview(showBackground = true, name = "HOT Repository Preview")
@Composable
private fun Preview2() {
    GithubTheme {
        GithubRepositoryItem(
            fullName = "next-step/nextstep-docs",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소 nextstep 매뉴얼 및 문서를 관리하는 저장소",
            startCount = 60,
            isHot = true
        )
    }
}
