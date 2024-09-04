package nextstep.github.ui.repository.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.R
import nextstep.github.ui.model.UiRepository

@Composable
fun RepositoryListItem(
    modifier: Modifier = Modifier,
    item: UiRepository,
) {
    Column(modifier = modifier.padding(16.dp)) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            if (item.isHot) {
                Text(
                    text = stringResource(id = R.string.hot),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.CenterStart),
                )
            }

            Star(stars = item.stars, modifier = Modifier.align(Alignment.CenterEnd))
        }

        Text(
            text = item.fullName,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = item.description ?: "-",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
private fun Star(
    modifier: Modifier = Modifier,
    stars: Int,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "starIcon",
            modifier = Modifier.size(18.dp),
        )
        Text(
            text = stringResource(id = R.string.number_format, stars),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier,
        )
    }
}

@Preview
@Composable
private fun RepositoryListItemPreview() {
    RepositoryListItem(
        item = UiRepository(
            fullName = "nextstep-nextstep-docs",
            description = "description",
            stars = 50,
        ),
    )
}
