package co.folto.gitfinder.ui.base

import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity


/**
 * Created by Daniel on 5/23/2017 for GitFInder project.
 */
open class BaseActivity: AppCompatActivity(){

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

}