package nextstep.github.ui.screen.github.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(
                modifier = modifier.fillMaxWidth(),
                text = stringResource(id = R.string.text_title),
//                textAlign = TextAlign.Center,
            )
        },
    )
}

@Preview
@Composable
private fun MainTopBarPreview() {
    MainTopBar()
}
