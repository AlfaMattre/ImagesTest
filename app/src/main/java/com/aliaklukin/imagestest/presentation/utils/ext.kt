package com.aliaklukin.imagestest.presentation.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.aliaklukin.imagestest.BuildConfig
import com.aliaklukin.imagestest.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop

fun String.checkEmail(): Boolean {
    val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    return emailRegex.matches(this)
}

fun String.checkPassword(): Boolean {
    val passwordRegex =
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&#])[A-Za-z\\d@\$!%*?&#]{6,12}$".toRegex()
    return passwordRegex.matches(this)
}

fun String.checkAge(): Boolean {
    val ageRegex = "^(1[89]|[2-9][0-9])$".toRegex()
    return ageRegex.matches(this)
}

fun Context.hideKeyboard(editText: EditText) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(editText.windowToken, 0)
}

fun ImageView.loadImage(url: String) {

    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.setStrokeWidth(5f)
    circularProgressDrawable.setCenterRadius(30f)
    circularProgressDrawable.setColorSchemeColors(
        ContextCompat.getColor(context, android.R.color.holo_purple)
    )
    circularProgressDrawable.start()

    val urlWithKey = url + "?key=${BuildConfig.API_KEY}"

    Glide.with(context)
        .load(urlWithKey)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .transform(CenterCrop())
        .placeholder(circularProgressDrawable)
        .error(R.drawable.ic_empty_image)
        .into(this)
}

fun View.visibility(isVisible: Boolean) {
    this.isVisible = isVisible
}