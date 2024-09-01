package nextstep.github.ui.repository

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RepositoryListScreen(
    modifier: Modifier = Modifier,
    viewModel: RepositoryListViewModel = viewModel(factory = RepositoryListViewModel.Factory),
) {
    val repositories by viewModel.repositories.collectAsStateWithLifecycle()
    Column(modifier = modifier) {
        repositories.forEach {
            Text(text = it.fullName ?: "-")
        }
    }
}
