package com.pustovit.customlayout.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth

@Composable
inline fun MyColumn(
    gap: Dp,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val myMeasurePolicy = remember(gap) { MyColumnMeasurePolicy(gap) }
    Layout(
        measurePolicy = myMeasurePolicy,
        modifier = modifier,
        content = content
    )
}

class MyColumnMeasurePolicy(private val gap: Dp) : MeasurePolicy {
    override fun MeasureScope.measure(
        measurables: List<Measurable>, constraints: Constraints
    ): MeasureResult {
        // Этап 1 Measure children - Измеряем дочерние элементы
        val placeables =
            measurables.map { measurable -> measurable.measure(constraints = constraints) }
        // Этап 2 Decide ownSize Родитель вычисляет собственный размер
        //  Ширина контента
        val contentWidth = placeables.maxOf { it.width }
        //  Высота контента
        val gapPx = gap.toPx().toInt()
        val contentHeight = placeables.sumOf { it.height } + gapPx * placeables.lastIndex
        val layoutWidth =
            constraints.constrainWidth(contentWidth)
        val layoutHeight =
            constraints.constrainHeight(contentHeight)
        // Этап 3 Place children - родитель размещает дочерние UI элементы
        return layout(width = layoutWidth, height = layoutHeight) {
            var y = 0
            placeables.forEach { placeable ->
                placeable.placeRelative(0, y = y)
                y += placeable.height + gapPx
            }
        }
    }
}