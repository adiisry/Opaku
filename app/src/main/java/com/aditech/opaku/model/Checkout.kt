package com.aditech.opaku.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Checkout (
    var judul:String?="",
    var harga:String?=""
) : Parcelable