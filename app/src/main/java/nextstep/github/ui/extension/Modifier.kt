package nextstep.github.ui.extension

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.bottomBorder(strokeWidth: Dp = 1.dp, color: Color = Color.Black): Modifier {
    return this.drawBehind {
        val strokeWidthPx = strokeWidth.toPx()
        val y = size.height - (strokeWidthPx / 2)

        drawLine(
            color = color,
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = strokeWidthPx
        )
    }
}