package com.kush.nytimes.extenstions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.Browser
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.kush.nytimes.R
import java.io.IOException

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun <T> Context.openActivity(
    it: Class<T>,
    extras: Bundle.() -> Unit = {}
) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}

fun Activity.openInCustomTab(string: String) = customTabsWeb(string)

private fun Context.customTabsWeb(string: String) {
    try {

        val builder = CustomTabsIntent.Builder()

        builder.setToolbarColor(
            Color.parseColor(
                "#" + Integer.toHexString(
                    ContextCompat.getColor(
                        this,
                        R.color.browser_actions_bg_grey
                    )
                )
            )
        )
        builder.setShowTitle(true)
        val intent = builder.build()
        intent.launchUrl(this, Uri.parse(string))

    } catch (e: IOException) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(string))
        intent.putExtra(Browser.EXTRA_CREATE_NEW_TAB, true)
        intent.putExtra(Browser.EXTRA_APPLICATION_ID, packageName)
        startActivity(intent)
    }
}