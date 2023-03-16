package com.seek.assignment.core.utils

import kotlin.math.PI
import kotlinx.datetime.LocalDateTime

const val missingStringValue = "bd64f2ef-2efa-4138-ad0a-af14a6cc7565"
const val missingIntValue = Int.MIN_VALUE
const val missingFloatValue = Float.MIN_VALUE
const val missingLongValue = Long.MIN_VALUE
const val missingDoubleValue = Double.MIN_VALUE

// Math constants
const val halfPiRad = PI.toFloat() / 2f
const val doublePiRad = PI.toFloat() * 2f
const val radPerDegree = PI.toFloat() / 180
////

val minLocalDateTime = LocalDateTime(1, 1, 1, 0, 0, 0)

const val feedMaximumDaysLimit = 28
const val accountNameCharLimit = 40
const val feedItemRecentDaysLimit = 3
const val zuluTimeMarker = 'Z'
const val namingCharacterLimit24 = 24

const val delimiterBracket = "}"
const val delimiterSemicolon = ";"
const val delimiterBulletItem = "{{BULLET:"
const val delimiterNewLine = "\n\n"
const val delimiterUnderscore = "_"
const val delimiterSpace = " "
const val delimiterForwardSlash = "/"

const val airBalloonVolume = 2200

const val VALUE_NA_PLACEHOLDER = "-"

const val waitingForApplianceProvisioningMinutes = 5
const val HOURS_IN_A_DAY = 24
const val DAYS_IN_A_MONTH = 30
const val MONTHS_IN_A_YEAR = 12
const val MINS_IN_A_HOUR = 60
const val DAYS_IN_A_WEEK = 7

const val SQUARE_FEET_IN_SQUARE_METER = 10.764
const val KILOMETERS_IN_MILE = 1.609

const val SECONDS_IN_AN_HOUR = 3600
const val SECONDS_IN_A_MINUTE = 60

const val DEGREES_IN_CIRCLE = 360f
const val QUARTER_CIRCLE = 90f

const val BOLD_TEXT_PATTERN = "(^| |\\n)\\*[^*]*\\*"

const val HOME_VIEW_STILL_LOADING_TIME_SECONDS = 5L