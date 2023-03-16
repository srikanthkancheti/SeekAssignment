package com.seek.assignment.android.components.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.seek.assignment.android.theme.AssignmentAppTheme
import com.seek.assignment.android.theme.toColor
import com.seek.assignment.android.theme.toTextStyle
import com.seek.assignment.android.theme.toSpanStyle
import com.seek.assignment.android.theme.toParagraphStyle
import com.seek.assignment.branding.AssignmentAppTypography
import com.seek.assignment.core.utils.BOLD_TEXT_PATTERN

enum class FontScale(val value: Float) {
    SMALL(0.85f),
    NORMAL(1.0f),
    LARGE(1.15f),
    EXTRA_LARGE(1.3f)
}

@Composable
fun TextView(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    style: AssignmentAppTypography,
    color: Long,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    isScalable: Boolean = true,
    maxFontScale: Float? = null,
    onClick: (() -> Unit)? = null
) {

    var textViewModifier = modifier

    if (onClick != null) {
        textViewModifier = textViewModifier.clickable(onClick = onClick)
    }

    val regex = BOLD_TEXT_PATTERN.toRegex()
    val boldTexts = regex.findAll(text).map { it.value }.toList()

    if (boldTexts.isEmpty()) {
        Text(
            modifier = textViewModifier,
            text = text,
            textAlign = textAlign,
            color = color.toColor(),
            style = style.toTextStyle(isScalable = isScalable, maxFontScale = maxFontScale),
            overflow = overflow,
            maxLines = maxLines
        )
    } else {
        BasicText(
            text = getAnnotatedString(text, boldTexts, style, color),
            modifier = textViewModifier,
            softWrap = true,
            overflow = overflow,
            maxLines = maxLines
        )
    }
}

fun getAnnotatedString(
    text: String,
    boldTexts: List<String>,
    style: AssignmentAppTypography,
    color: Long
): AnnotatedString {
    val boldStyle = AssignmentAppTheme.typography.getBoldVariant(style)
    var finalText = text

    val annotatedString: AnnotatedString = buildAnnotatedString {
        var startIndex = 0

        boldTexts.forEach { boldText ->
            var index = finalText.indexOf(boldText, startIndex)

            append(finalText.substring(startIndex, index))
            addStyle(style = style.toSpanStyle(color), start = startIndex, end = index)

            val beforeChar = boldText[0]
            val afterChar = boldText.last()
            val simpleText = boldText
                .removePrefix(if (beforeChar == '*') "*" else "$beforeChar*")
                .removeSuffix(if (afterChar == '*') "*" else "*$afterChar")

            finalText = finalText.replaceFirst(boldText, "${if (beforeChar != '*') "$beforeChar" else ""}$simpleText${if (afterChar != '*') "$afterChar" else ""}")

            if (beforeChar != '*') {
                append(beforeChar)
                addStyle(style = style.toSpanStyle(color), start = index, end = index + 1)
                index += 1
            }

            append(simpleText)
            addStyle(style = boldStyle.toSpanStyle(color), start = index, end = index + simpleText.length)

            if (afterChar != '*') {
                append(afterChar)
                addStyle(style = style.toSpanStyle(color), start = index + simpleText.length, end = index + simpleText.length + 1)
                index += 1
            }

            startIndex = index + simpleText.length
        }

        if (startIndex != finalText.length) {
            append(finalText.substring(startIndex))
            addStyle(style = style.toSpanStyle(color), start = startIndex, end = finalText.length)
        }

        addStyle(style = style.toParagraphStyle(), start = 0, end = finalText.length)
    }

    return annotatedString
}
