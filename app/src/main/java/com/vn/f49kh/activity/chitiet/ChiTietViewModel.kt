package com.vn.f49kh.activity.chitiet

import android.app.Application
import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.vn.f49kh.R
import com.vn.f49kh.activity.BaseF49ViewModel
import com.vn.f49kh.extension.toPrice
import com.vn.f49kh.model.chitiet.ChiTietDTO
import com.vn.f49kh.model.chitiet.ChiTietDongLaiDTO
import com.vn.f49kh.model.image.ImageDTO
import com.vn.f49kh.utils.Constant
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.security.auth.callback.Callback


class ChiTietViewModel(app: Application) : BaseF49ViewModel(app) {
    var chiTietDongLai: MutableLiveData<ChiTietDongLaiDTO?> = MutableLiveData()
    var chiTietThanhLy: MutableLiveData<MutableList<ChiTietDTO>?> = MutableLiveData()
    var listImage: MutableLiveData<MutableList<String>> = MutableLiveData()
    var price: MutableLiveData<String> = MutableLiveData()
    var nameProduct: MutableLiveData<String> = MutableLiveData()

    fun getChiTietDongLai(idHopDOng: Int?) {
//        showLoading()
        handleRequestServiceObject(mApiService.getChiTietDongLai(idHopDOng)) {
            chiTietDongLai?.value = it
        }
    }

    fun getChiTietThanhLy(idHopDOng: Int?) {
        showLoading()
        Handler().postDelayed(Runnable {
            mApiService?.getChiTietThanhLy(idHopDOng).enqueue(object : Callback, retrofit2.Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    hideLoading()
                    showDialog(t.message ?: mContext.getString(R.string.server_error))

                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    hideLoading()
                    if (response.isSuccessful) {
                        var list = mutableListOf<ChiTietDTO>();
                        var json = JSONObject(response.body()?.string())
                        if (response.code() == 200) {
                            var data = json.getString("data")
                            if(data!="null") {
                                var jsonObject = JSONObject(data)
                                var keys = jsonObject.keys();
                                while (keys.hasNext()) {
                                    var key = keys.next()
                                    if (key == "listImage") {
                                        var image = jsonObject.get(key).toString()
                                        if (!image.equals("null")) {
                                            var l = Gson().fromJson(image, Array<ImageDTO>::class.java).asList()
                                            listImage.value = l.toMutableList().map { it.url ?:"" }.toMutableList()
                                        } else {
                                            listImage.value = mutableListOf()
                                        }
                                        continue
                                    }
                                    if (key == "giaThanhLy") {
                                        var gia = jsonObject.getLong(key)
                                        price.value = gia.toPrice()
                                        continue
                                    }
                                    if (key == "tenVatCamCo") {
                                        var name = jsonObject.getString(key)
                                        nameProduct.value = name
                                        continue
                                    }
                                    var value = jsonObject.get(key).toString()
                                    if (!value.equals("null")) {
                                        if (!value.trim().isNullOrEmpty()) {
                                            var chitiet = ChiTietDTO()
                                            if (key.equals("ngayLuuKho") || key.equals("ngayCapNhat") || key.equals("ngayTao")) {
                                                value = convertDateToString(convertStringToDate(value))
                                            }
                                            chitiet.value = value
                                            chitiet.key = key
                                            list.add(chitiet)
                                        }
                                    }

                                }
                            }
                            chiTietThanhLy.value = list
                        } else {
                            showDialog(json.getString("message"))
                        }
                    }
                }

            })
        }, 100)

    }

    fun convertStringToDate(dtStart: String): Date? {
        val format = SimpleDateFormat(Constant.FORMAT_DATE_TIME_ISO)
        try {
            val date = format.parse(dtStart)
            return date
        } catch (e: ParseException) {
            e.printStackTrace()
            return null
        }
    }

    fun convertDateToString(date: Date?): String {
        val dateFormat = SimpleDateFormat(Constant.FORMAT_DATE_TIME_TO_SHOW)
        try {
            val dateTime = dateFormat.format(date)
            return dateTime
        } catch (e: ParseException) {
            e.printStackTrace()
            return ""
        }

    }

}