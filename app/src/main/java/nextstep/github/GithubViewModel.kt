package nextstep.github

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nextstep.github.core.data.GithubRepository
import nextstep.github.core.data.GithubRepositoryInfo
import nextstep.github.core.network.ApiResult

class GithubViewModel(
    private val githubRepository: GithubRepository
) : ViewModel() {

    private val _githubRepositoryInfoList =
        MutableStateFlow<List<GithubRepositoryInfo>>(emptyList())
    val githubRepositoryInfoList: StateFlow<List<GithubRepositoryInfo>> = _githubRepositoryInfoList

    fun getRepositories(organization: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val result = githubRepository.getRepositories(organization)) {
                    is ApiResult.Success -> {
                        _githubRepositoryInfoList.value = result.value.map {
                            GithubRepositoryInfo(
                                fullName = it.fullName ?: "",
                                description = it.description ?: ""
                            )
                        }
                        Log.d("GithubViewModel", "Success: ${result.value}")
                    }

                    is ApiResult.Error -> {
                        Log.e("GithubViewModel", "Error1: ${result.code} ${result.exception}")
                    }
                }
            } catch (e: Exception) {
                Log.e("GithubViewModel", "Error2: ${e.message}")
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository = (this[APPLICATION_KEY] as MainApplication)
                    .appContainer
                    .githubRepository

                GithubViewModel(githubRepository)
            }
        }
    }
}
