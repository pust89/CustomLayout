package com.pustovit.customlayout.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

}

class MyColumnMeasurePolicy(private val gap: Dp) : MeasurePolicy {

    override fun MeasureScope.measure(
        measurables: List<Measurable>,
        constraints: Constraints
    ): MeasureResult {

        // Этап 1 Measure children - Измеряем дочерние элементы
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints = constraints)
        }

        // Этап 2 Decide ownSize Родитель вычисляет собственный размер
        //  Ширина контента
        val contentWidth = placeables.maxOf { it.width }

        //  Высота контента
        val gapPx = gap.toPx().toInt()
        val contentHeight = placeables.sumOf { it.height } + gapPx * placeables.size

        val layoutWidth =
            constraints.constrainWidth(contentWidth)//contentWidth.coerceIn(constraints.minWidth, constraints.maxWidth)
        val layoutHeight =
            constraints.constrainHeight(contentHeight)//contentHeight.coerceIn(constraints.minHeight, constraints.maxHeight)

        return layout(width = layoutWidth, height = layoutHeight) {
            val lastIndex = placeables.lastIndex
            var y = 0
            placeables.forEachIndexed { index, placeable ->
                placeable.placeRelative(0, y = y)
                y += if (index != lastIndex) placeable.height + gapPx else placeable.height
            }
        }
    }

}