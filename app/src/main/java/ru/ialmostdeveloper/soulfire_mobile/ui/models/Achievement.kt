package ru.ialmostdeveloper.soulfire_mobile.ui.models

class Achievement(
    private var mImageDrawable: Int,
    private var mName: String, mRelease: String
) {

    private var mDescription: String
    fun getmImageDrawable(): Int {
        return mImageDrawable
    }

    fun setmImageDrawable(mImageDrawable: Int) {
        this.mImageDrawable = mImageDrawable
    }

    fun getmName(): String {
        return mName
    }

    fun setmName(mName: String) {
        this.mName = mName
    }

    fun getmDescription(): String {
        return mDescription
    }

    fun setmDescription(mRelease: String) {
        this.mDescription = mRelease
    }

    // Constructor that is used to create an instance of the Movie object
    init {
        mName = mName
        this.mDescription = mRelease
    }
}