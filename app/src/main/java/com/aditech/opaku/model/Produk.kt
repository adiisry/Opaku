package com.aditech.opaku.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Produk (
    var foto:String?="",
    var judul:String?="",
    var harga:String?="",
    var desc:String?=""
) : Parcelable