package com.kush.nytimes.networking

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class ErrorResponse(
    var code: String? = null,
    var detail: String? = null
) : Parcelable

