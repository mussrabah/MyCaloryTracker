package com.musscoding.tracker_presentation.tracker_overview.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.musscoding.core.R
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter

@Composable
fun parseDateText(
    date: LocalDate
): String {
    val today = LocalDate.now()
    return when(date) {
        today -> stringResource(id = R.string.today)
        today.minusDays(1) -> stringResource(id = R.string.yesterday)
        today.plusDays(1) -> stringResource(id = R.string.tomorrow)
        LocalDate.of(today.year + 1, Month.JANUARY, 1) -> DateTimeFormatter
            .ofPattern("dd LLLL y")
            .format(date)
        LocalDate.of(today.year - 1, Month.DECEMBER, 31) -> DateTimeFormatter
            .ofPattern("dd LLLL y")
            .format(date)
        else -> DateTimeFormatter.ofPattern("dd LLLL").format(date)
    }
}