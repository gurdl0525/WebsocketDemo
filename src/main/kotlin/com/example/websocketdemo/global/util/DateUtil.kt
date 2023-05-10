package com.example.websocketdemo.global.util

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

object DateUtil {
    fun getZonedNow(): LocalDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"))

    fun emeriedFormat(localDateTime: LocalDateTime): String =
        localDateTime.format(
            DateTimeFormatter.ofPattern("a HH:mm").withLocale(Locale.forLanguageTag("ko"))
        )

    fun toTimeAgoFormat(localDateTime: LocalDateTime): String {

        val now: LocalDateTime = getZonedNow()

        return if (localDateTime.year != now.year) {

            yearDifference(now, localDateTime).toString() + "년전"

        } else if (getIntMonth(localDateTime) != getIntMonth(now)) {

            monthDifference(now, localDateTime).toString() + "달전"

        } else if (localDateTime.dayOfMonth != now.dayOfMonth) {

            dayDifference(now, localDateTime).toString() + "일전"

        } else if (localDateTime.hour != now.hour) {

            hourDifference(now, localDateTime).toString() + "시간전"

        } else if (localDateTime.minute != now.minute) {

            minuteDifference(now, localDateTime).toString() + "분전"

        } else { "방금전" }
    }

    private fun yearDifference(localDateTime1: LocalDateTime, localDateTime2: LocalDateTime) =
        localDateTime1.minusYears(localDateTime2.year.toLong()).year

    private fun monthDifference(localDateTime1: LocalDateTime, localDateTime2: LocalDateTime) =
        getIntMonth(localDateTime1.minusMonths(getIntMonth(localDateTime2).toLong()))

    private fun getIntMonth(localDateTime: LocalDateTime) =
        localDateTime.month.value

    private fun dayDifference(localDateTime1: LocalDateTime, localDateTime2: LocalDateTime) =
        localDateTime1.minusDays(localDateTime2.dayOfMonth.toLong()).dayOfMonth

    private fun hourDifference(localDateTime1: LocalDateTime, localDateTime2: LocalDateTime) =
        localDateTime1.minusHours(localDateTime2.hour.toLong()).hour

    private fun minuteDifference(localDateTime1: LocalDateTime, localDateTime2: LocalDateTime) =
        localDateTime1.minusMinutes(localDateTime2.minute.toLong()).minute
}