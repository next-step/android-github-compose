package nextstep.github.ui.repository.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryListTopBar() {
    CenterAlignedTopAppBar(title = { Text(text = stringResource(id = R.string.nextstep_repositories)) })
}

@Preview
@Composable
private fun RepositoryListTopBarPreview() {
    RepositoryListTopBar()
}
