package com.rashikaranpuria.wanderee.data.api.model

import android.os.Parcel
import android.os.Parcelable
import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class VariationsItem(

	@field:SerializedName("default")
	val jsonMemberDefault: Int,

	@field:SerializedName("isVeg")
	val isVeg: Int,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("inStock")
	val inStock: Int,

	@field:SerializedName("id")
	val id: String
) : Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readInt(),
			parcel.readInt(),
			parcel.readInt(),
			parcel.readString(),
			parcel.readInt(),
			parcel.readString()) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeInt(jsonMemberDefault)
		parcel.writeInt(isVeg)
		parcel.writeInt(price)
		parcel.writeString(name)
		parcel.writeInt(inStock)
		parcel.writeString(id)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<VariationsItem> {
		override fun createFromParcel(parcel: Parcel): VariationsItem {
			return VariationsItem(parcel)
		}

		override fun newArray(size: Int): Array<VariationsItem?> {
			return arrayOfNulls(size)
		}
	}
}