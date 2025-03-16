package nextstep.github.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import nextstep.github.R
import nextstep.github.ui.model.GithubRepositoryModel
import nextstep.github.ui.preview.GithubRepositoryItemParameterProvider
import nextstep.github.ui.theme.GithubTheme
import nextstep.github.ui.theme.Typography

@Composable
internal fun GithubRepositoryItem(
    model: GithubRepositoryModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        model.starCount?.let {
            StarCountRow(count = it, showHotKeyword = model.showHotKeyword())
        }

        Text(
            text = model.fullName.orEmpty(),
            style = Typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = model.description.orEmpty(),
            style = Typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun StarCountRow(count: Int, showHotKeyword: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (showHotKeyword) {
            Text(
                text = stringResource(R.string.hot),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        Spacer(modifier = Modifier.weight(1f, true))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = stringResource(R.string.star),
                tint = Color(0xFF000000),
                modifier = Modifier.size(18.dp),
            )
            Text(
                text = count.toString(),
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoryItemPreview(
    @PreviewParameter(GithubRepositoryItemParameterProvider::class)
    model: GithubRepositoryModel
) {
    GithubTheme {
        GithubRepositoryItem(model)
    }
}