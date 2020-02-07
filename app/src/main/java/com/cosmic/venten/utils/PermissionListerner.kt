package com.cosmic.venten.utils

interface PermissionListener {

    fun onPermissionCheckCompleted(requestCode: Int, isPermissionGranted: Boolean)
}