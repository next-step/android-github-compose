package nextstep.github.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.R
import nextstep.github.ui.components.GithubTopBar
import nextstep.github.ui.home.model.dummyData
import nextstep.github.ui.theme.GithubTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory),
    modifier: Modifier = Modifier
) {

    val homeUiState by viewModel.homeUiState.collectAsStateWithLifecycle()

    viewModel.fetchRepos("next-step")

    HomeScreen(
        homeUiState = homeUiState,
        modifier = modifier
    )

}

@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            GithubTopBar(
                title = stringResource(id = R.string.github_top_bar_title)
            )
        },
        modifier = modifier
    ) { innerPadding ->
        when (homeUiState) {
            is HomeUiState.HasRepos -> {
                LazyColumn(
                    modifier = Modifier.padding(innerPadding)
                ) {
                    items(homeUiState.githubRepos) { githubRepo ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = githubRepo.fullName,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = githubRepo.description,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        HorizontalDivider()
                    }
                }
            }

            is HomeUiState.NoRepos -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = homeUiState.errorMessage)
                }
            }
        }

    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
private fun HomeScreenPreview() {

    val homeUiState by mutableStateOf(HomeUiState.HasRepos(dummyData))

    GithubTheme {
        HomeScreen(homeUiState = homeUiState)
    }
}
