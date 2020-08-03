package com.herace.android_boilerplate.ui.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Commons {

    fun intToString(number: Int) = number.toString()

    fun intNumberToCommaString(intNumber: Int?): String? {
        return if (intNumber != null) NumberFormat.getNumberInstance(Locale.KOREA).format(intNumber)
        else "0"
    }

    fun getColor(isScrap: Boolean): Int {
        return if (isScrap) Color.RED
        else Color.GREEN
    }

    fun checkImgUrlExtension(imgUrl: String?): String {
        val extension =  imgUrl?.substringAfterLast(".")

        if(extension == "jpg" || extension == "png") {
            return imgUrl
        }

        return ""
    }

    fun readCount(read: Int?): String {

        val number = intNumberToCommaString(read)

        return "조회 ${number}회"
    }

    fun commentCount(comment: Int?): String {

        val number = intNumberToCommaString(comment)

        return "댓글 ${number}개"
    }

    fun likeCount(like: Int?): String {

        val number = intNumberToCommaString(like)

        return "좋아요 ${number}개"
    }

    @SuppressLint("SimpleDateFormat")
    fun transTime(time: String): String {

        val locale = Locale.getDefault()
        val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", locale)
        parser.timeZone = TimeZone.getTimeZone("UTC")

        val calendar = Calendar.getInstance()
        calendar.time = parser.parse(time)

        return timeToString(calendar.timeInMillis)
    }

    @SuppressLint("SimpleDateFormat")
    private fun timeToString(regTime: Long): String {

//        val formatter = SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초")
        val locale = Locale.getDefault()
        val formatter = SimpleDateFormat("yyyy/MM/dd", locale)
        val formattedDate = formatter.format(regTime)

        val currentTime = System.currentTimeMillis()
        val diffTime = (currentTime - regTime) / 1000

        val hour = diffTime / 3600;
        val gap = diffTime % 3600;
        val min = gap / 60;
        val sec = gap % 60;

        return  if (hour > 24) formattedDate
        else if (hour > 0) "${hour}시간 전"
        else if (min > 0) "${min}분 전"
        else if (sec > 0) "${sec}초 전"
        else formattedDate
    }

    @SuppressLint("SimpleDateFormat")
    fun transTimeNotTime(time: String): String {

        val locale = Locale.getDefault()
        val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", locale)
        parser.timeZone = TimeZone.getTimeZone("UTC")

        val calendar = Calendar.getInstance()
        calendar.time = parser.parse(time)

        return timeToStringNotTime(calendar.timeInMillis)
    }


    @SuppressLint("SimpleDateFormat")
    private fun timeToStringNotTime(regTime: Long): String {

//        val formatter = SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초")
        val locale = Locale.getDefault()
        val formatter = SimpleDateFormat("yy/MM/dd", locale)
        val formattedDate = formatter.format(regTime)

        val currentTime = System.currentTimeMillis()
        val diffTime = (currentTime - regTime) / 1000

        val hour = diffTime / 3600;
        val gap = diffTime % 3600;
        val min = gap / 60;
        val sec = gap % 60;

        return  if (hour > 24) formattedDate
        else if (hour > 0) "${hour}시간 전"
        else if (min > 0) "${min}분 전"
        else if (sec > 0) "${sec}초 전"
        else formattedDate
    }


    fun getDiscountRatio(ori: BigDecimal?, discount: BigDecimal?): String {
        return if (ori != null && discount != null && ori > 0.toBigDecimal() && discount > 0.toBigDecimal())
            "${(100.toBigDecimal() - ori.divide(discount).multiply(100.toBigDecimal())).setScale(0, RoundingMode.FLOOR)}%"
        else "0%"
    }
    
    fun getGoodsPrice(price: BigDecimal?): String {
        return if (price != null) "${intNumberToCommaString((price.setScale(0, RoundingMode.FLOOR)).toInt())}원"
        else "0원"
    }

    @Suppress("DEPRECATION")
    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }
}