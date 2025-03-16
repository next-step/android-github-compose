package nextstep.github.ui.screen.github.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.R
import nextstep.github.ui.theme.black

@Composable
fun GithubRepoItem(
    fullName: String,
    description: String,
    isHot: Boolean,
    starCount: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if(isHot) {
                    Text(
                        text = stringResource(R.string.is_hot_text),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.W500,
                    )
                } else {
                    Spacer(modifier = Modifier)
                }

                StarCountRow(
                    starCount = starCount,
                )
            }
            Text(
                text = fullName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.W400
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        HorizontalDivider(
            color = MaterialTheme.colorScheme.outlineVariant,
        )
    }
}

@Composable
private fun StarCountRow(
    starCount: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Star",
            tint = MaterialTheme.colorScheme.black,
        )
        Text(
            text = "$starCount",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.W500
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun StarCountRowPreview() {
    StarCountRow(
        starCount = 100,
    )
}

@Preview(showBackground = true)
@Composable
private fun GithubRepoItemIsHotPreview() {
    GithubRepoItem(
        fullName = "next-step/nextstep-docs",
        description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
        isHot = true,
        starCount = 100,
    )
}

@Preview(showBackground = true)
@Composable
private fun GithubRepoItemNotHotPreview() {
    GithubRepoItem(
        fullName = "next-step/nextstep-docs",
        description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
        isHot = false,
        starCount = 20,
    )
}
