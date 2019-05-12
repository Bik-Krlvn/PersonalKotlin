package com.cheiseproj.bik_krl.personalkotlin.utils.provider


import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object SimpleDateProvider {
    private var simpleDateFormat: SimpleDateFormat? = null
    fun getYear(date: Date?): String? {
        if (date == null) return null
        simpleDateFormat = SimpleDateFormat("yyyy", Locale.getDefault())
        return simpleDateFormat!!.format(date)
    }

    fun getShortDate(date: Date?): String? {
        if (date == null) return null
        simpleDateFormat = SimpleDateFormat("EE, MMM dd", Locale.getDefault())
        return simpleDateFormat!!.format(date)
    }

    fun getDay(date: Date?): String? {
        if (date == null) return null
        simpleDateFormat = SimpleDateFormat("EEE", Locale.getDefault())
        return simpleDateFormat!!.format(date)
    }

    fun getDayNumber(date: Date?): String? {
        if (date == null) return null
        simpleDateFormat = SimpleDateFormat("dd", Locale.getDefault())
        return simpleDateFormat!!.format(date)
    }

    fun getShortTime(date: Date?): String? {
        if (date == null) return null
        simpleDateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return simpleDateFormat!!.format(date)
    }

    fun getLongDate(date: Date?): String? {
        if (date == null) return null
        simpleDateFormat = SimpleDateFormat("EEEE, MMMM dd, y", Locale.getDefault())
        return simpleDateFormat!!.format(date)
    }

    fun getFormattedDate(smsTimeInMilis: Long): String {
        val currentTime = Calendar.getInstance()
        currentTime.timeInMillis = smsTimeInMilis
        val now = Calendar.getInstance()
        val timeFormatString = "h:mm aa"
        val dateTimeFormatString = "EEEE, MMMM d, h:mm aa"
        return when {
            now.get(Calendar.DATE) == currentTime.get(Calendar.DATE) -> {
                "Today " + DateFormat.format(timeFormatString, currentTime)
            }
            now.get(Calendar.DATE) - currentTime.get(Calendar.DATE) == 1 ->{
                "Yesterday " + DateFormat.format(timeFormatString, currentTime)
            }
            now.get(Calendar.YEAR) == currentTime.get(Calendar.YEAR) ->{
                DateFormat.format(dateTimeFormatString, currentTime).toString()
            }
            else -> DateFormat.format("MMMM dd yyyy, h:mm aa", currentTime).toString()
        }
    }
}
