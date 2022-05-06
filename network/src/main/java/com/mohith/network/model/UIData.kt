package com.mohith.network.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class UIData(
    @SerializedName("uitype")
    val uiType: UITYPE,
    var value: String? = null,
    val key: String? = null,
    val hint: String? = null
):Parcelable {
    constructor(parcel: Parcel) : this(
        UITYPE.values()[parcel.readInt()],
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(uiType.ordinal);
        parcel.writeString(value)
        parcel.writeString(key)
        parcel.writeString(hint)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UIData> {
        override fun createFromParcel(parcel: Parcel): UIData {
            return UIData(parcel)
        }

        override fun newArray(size: Int): Array<UIData?> {
            return arrayOfNulls(size)
        }
    }
}
