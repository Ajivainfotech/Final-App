package com.example.haggerplanet.views.termsAndConditions

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.lifecycle.ViewModel

class TermsAndConitionsVM(val context: Context,val termsAndConditions: TermsAndConditions):ViewModel() {

    init {
        loadWeview()
    }

    fun loadWeview(){
        termsAndConditions.termsAndConditionsBinding!!.webviewTerms.settings.javaScriptEnabled = true // enable javascript
        termsAndConditions.termsAndConditionsBinding!!.webviewTerms.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                    view: WebView?,
                    errorCode: Int,
                    description: String?,
                    failingUrl: String?
            ) {
                Toast.makeText(context, description, Toast.LENGTH_SHORT).show()
            }

            override fun onPageCommitVisible(view: android.webkit.WebView?, url: String?) {
                super.onPageCommitVisible(view, url)

                Log.e("Visible","-------------->")
                termsAndConditions.termsAndConditionsBinding!!.progress.visibility= View.GONE
            }


            @TargetApi(Build.VERSION_CODES.M)
            override fun onReceivedError(
                    view: WebView?,
                    req: WebResourceRequest,
                    rerr: WebResourceError
            ) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(
                        view,
                        rerr.errorCode,
                        rerr.description.toString(),
                        req.url.toString()
                )
            }
        }

        termsAndConditions.termsAndConditionsBinding!!.webviewTerms.loadUrl("https://hagglerplanet.com/page/shipping")
    }
}