package com.aditech.opaku.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Banner (
    var poster:String?="",
    var judul:String?=""
) : Parcelable