package nextstep.github.ui.model

import androidx.annotation.StringRes

sealed interface RepositoryListEvent {
    data class ShowSnackBar(@StringRes val msgRes: Int, @StringRes val actionLabelRes: Int): RepositoryListEvent
}
