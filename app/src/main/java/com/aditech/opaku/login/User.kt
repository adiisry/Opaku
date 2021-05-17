package com.aditech.opaku.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User (
    var nama:String ?="",
    var email:String ?="",
    var username:String ?="",
    var password:String ?="",
    var address:String ?="",
    var url:String ?=""

) : Parcelable