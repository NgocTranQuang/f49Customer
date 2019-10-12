package com.vn.f49kh.extension

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vn.f49kh.R
import com.vn.f49kh.deco.DividerItemDecoration
import com.vn.f49kh.utils.Constant
import com.vn.f49kh.utils.ImageUtil
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun Double.toPrice(): String {
    return formatVND(this)
}

fun Long.toPrice(): String {
    return formatVND(this)
}

fun formatVND(price: Double): String {
    return formatVND(price.toLong())
}

fun formatVND(price: Long): String {
    try {
        var moneyUSD = NumberFormat.getCurrencyInstance(Locale.US).format(price)
        moneyUSD = moneyUSD.replace("$", "")
        if (moneyUSD.endsWith(".00")) {
            val centsIndex = moneyUSD.lastIndexOf(".00")
            if (centsIndex != -1) {
                moneyUSD = moneyUSD.substring(0, centsIndex)
            }
        }
        moneyUSD = moneyUSD.replace(",", ".")
//        moneyUSD = String.format("%s đ", moneyUSD)
        return moneyUSD
    } catch (e: Exception) {
        return "0"
    }

}

@SuppressLint("WrongConstant")
fun RecyclerView.init(intver: Int = LinearLayoutManager.VERTICAL, space: Int = R.dimen.height_line_size) {
    layoutManager = LinearLayoutManager(context, intver, false)
    var decoration = DividerItemDecoration(context.resources.getDimensionPixelSize(space), intver)
    addItemDecoration(decoration)
}

fun Date.format(format: String): String {
    val sdf = SimpleDateFormat(format)
    val formattedDate = sdf.format(this)
    return formattedDate
}

fun Date.toStringISO(): String {
    val sdf = SimpleDateFormat(Constant.FORMAT_DATE_TIME_ISO)
    val formattedDate = sdf.format(this)
    return formattedDate
}

fun Date.toShow(): String {
    return format(Constant.FORMAT_DATE_TIME_TO_SHOW)
}

fun ImageView.toArrowExpand() {
    ImageUtil.animateArrowExpand(this)
}

fun ImageView.toArrowCollapse() {
    ImageUtil.animateArrowCollapse(this)
}