package com.vn.f49kh.extension

import android.annotation.SuppressLint
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vn.custom.util.GeneralUtil
import com.vn.f49kh.R
import com.vn.f49kh.activity.login.LoginActivity
import com.vn.f49kh.deco.DividerItemDecoration
import com.vn.f49kh.service.GeneralUtils
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
        return moneyUSD + " VND"
    } catch (e: Exception) {
        return "0"
    }

}

fun formatMoney(price: Double): String {
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

fun EditText.addCurrencyFormatter() {

    // Reference: https://stackoverflow.com/questions/5107901/better-way-to-format-currency-input-edittext/29993290#29993290
    this.addTextChangedListener(object : TextWatcher {

        private var current = ""

        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            if (s.toString() != current) {
                this@addCurrencyFormatter.removeTextChangedListener(this)
                // strip off the currency symbol

                // Reference for this replace regex: https://stackoverflow.com/questions/5107901/better-way-to-format-currency-input-edittext/28005836#28005836
                val cleanString = s.toString().replace("\\D".toRegex(), "")
                val parsed = if (cleanString.isBlank()) 0.0 else cleanString.toDouble()

                current = formatMoney(parsed)


                this@addCurrencyFormatter.setText(current)
                this@addCurrencyFormatter.setSelection(current.length)

                this@addCurrencyFormatter.addTextChangedListener(this)
            }
        }
    })

}

fun AppCompatActivity.checkLogin(todo: () -> Unit) {
    if (GeneralUtil.isLogined(this)) {
        startActivity(Intent(this, LoginActivity::class.java))
    } else {
        todo.invoke()
    }
}

fun EditText.isPhoneNumber(): Boolean {
    return GeneralUtils.isPhoneNumberFormat(this)
}

fun EditText.isEmailAddress(): Boolean {
    return GeneralUtils.isMailAddress(this.text.toString())
}
