package com.vn.f49kh.activity.webview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.widget.Toolbar
import com.vn.f49kh.R
import com.vn.f49kh.activity.BaseF49Activity
import com.vn.f49kh.databinding.ActivityWebviewBinding
import kotlinx.android.synthetic.main.activity_webview.*

class WebViewActivity : BaseF49Activity<ActivityWebviewBinding, WebViewViewModel>() {

    override fun getLayout(): Int {
        return R.layout.activity_webview
    }

    companion object {
        val LINK = "LINK"
        fun start(context: Context?, linkWeb: String?) {
            context?.startActivity(Intent(context, WebViewActivity::class.java).putExtra(LINK, linkWeb))
        }
    }

    override fun getMyToolbar(): Toolbar? {
        return tb
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLoading()
        wv.settings.javaScriptEnabled = true
        wv.settings.setSupportZoom(false)

        wv.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                hideLoading()
                title = view?.title
            }
        }

//        wv.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//                view?.loadUrl(url)
//                return true
//            }
//        }
        wv.loadUrl("https://stackoverflow.com/questions/8193239/how-to-get-loaded-web-page-title-in-android-webview")
    }
}