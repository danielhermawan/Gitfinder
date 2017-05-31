package co.folto.gitfinder.util

import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import co.folto.gitfinder.R

/**
 * Created by Daniel on 5/24/2017 for GitFInder project.
 */
fun ViewGroup.inflate(layoutId: Int): View
        = LayoutInflater.from(context).inflate(layoutId, this, false)

fun View.showSnack(message: String, duration: Int = Snackbar.LENGTH_SHORT)
        = Snackbar.make(this, message, duration).show()

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT)
        = Toast.makeText(this, message, duration).show()

fun FragmentManager.addToActitivity(fragment: Fragment, frameId: Int)
        = this.beginTransaction().add(frameId, fragment).commit()

fun SwipeRefreshLayout.setDefaultColors(context: Context)
        = this.setColorSchemeColors(
        ContextCompat.getColor(context, R.color.colorPrimary),
        ContextCompat.getColor(context, R.color.colorAccent),
        ContextCompat.getColor(context, R.color.colorPrimaryDark))

@TargetApi(Build.VERSION_CODES.M)
fun AppCompatActivity.requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            this.requestPermissions(permissions, requestCode)
    }

@TargetApi(Build.VERSION_CODES.M)
fun AppCompatActivity.hasPermission(permission: String)
        = Build.VERSION.SDK_INT < Build.VERSION_CODES.M
            || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED