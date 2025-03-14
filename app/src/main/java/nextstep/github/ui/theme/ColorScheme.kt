package nextstep.github.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val ColorScheme.topAppBarContainer: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF121212) else Color(0xFFFFFFFF)
