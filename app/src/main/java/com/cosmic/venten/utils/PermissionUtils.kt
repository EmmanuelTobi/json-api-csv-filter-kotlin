package com.cosmic.venten.utils

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.text.SimpleDateFormat
import java.util.*

open class PermissionUtils : AppCompatActivity() {

    protected var hasStoragePermissions: Boolean = false
    protected var hasAllPermissions: Boolean = false
    protected val REQUEST_PERMISSIONS_STORAGE = 7
    protected val REQUEST_PERMISSIONS = 1

    private var permissionListener: PermissionListener? = null

    fun getStoragePermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS_STORAGE)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_PERMISSIONS) {
            hasAllPermissions = (grantResults[1] == PackageManager.PERMISSION_GRANTED
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[2] == PackageManager.PERMISSION_GRANTED
                    && grantResults[3] == PackageManager.PERMISSION_GRANTED
                    && grantResults[4] == PackageManager.PERMISSION_GRANTED
                    && grantResults[5] == PackageManager.PERMISSION_GRANTED
                    && grantResults[6] == PackageManager.PERMISSION_GRANTED
                    && grantResults[7] == PackageManager.PERMISSION_GRANTED
                    && grantResults[8] == PackageManager.PERMISSION_GRANTED
                    && grantResults[9] == PackageManager.PERMISSION_GRANTED)
            try {
                permissionListener!!.onPermissionCheckCompleted(requestCode, hasAllPermissions)
            } catch (ignored: Exception) {

            }
        }

        if (requestCode == REQUEST_PERMISSIONS_STORAGE) {
            if (grantResults.size == 1) {
                hasStoragePermissions = grantResults[0] == PackageManager.PERMISSION_GRANTED

                try {

                    permissionListener!!.onPermissionCheckCompleted(requestCode, hasStoragePermissions)

                } catch (ignored: Exception) {

                }
            }
        }
    }
}
