package co.folto.gitfinder.util

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/**
 * Created by Daniel on 5/24/2017 for GitFInder project.
 */
fun ViewGroup.inflate(layoutId: Int): View
        = LayoutInflater.from(context).inflate(layoutId, this, false)

fun CoordinatorLayout.showToast(message: String, duration: Int = Snackbar.LENGTH_SHORT)
        = Snackbar.make(this, message, duration).show()

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT)
        = Toast.makeText(this, message, duration).show()