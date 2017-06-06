package co.folto.gitfinder.util

import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.support.customtabs.CustomTabsIntent
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import co.folto.gitfinder.R
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import org.joda.time.DateTime
import java.util.*

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

fun Resources.obtainDrawable(id: Int, context: Context): Drawable =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            this.getDrawable(id, context.getTheme())
        else
            this.getDrawable(id)

fun String.getUrlImagePlaceholder(): String {
    val colors = arrayOf("FF4081", "3F51B5", "4caf50", "ffeb3b")
    val random = Random().nextInt(colors.size)
    return "http://placehold.it/50x50/${colors[random]}/ffffff/&text=${this[0].toUpperCase()}"
}

fun ImageView.loadNetworkImage(context: Context,
                               url: String,
                               placeholder: Int = R.drawable.bitmap_image_loading,
                               errorImage: Int = R.drawable.bitmap_image_unavailable,
                               options: RequestOptions = RequestOptions())
        = GlideApp.with(context)
            .load(url)
            .placeholder(placeholder)
            .error(errorImage)
            .apply(options)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            /*.transition(withCrossFade())*/
            .into(this)

fun Context.openChromeTabs(url: String) {
    val builder = CustomTabsIntent.Builder()
    builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
    builder.addDefaultShareMenuItem();
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(this, Uri.parse(url));
}

fun String.formatDate(format: String)
    = DateTime(this).toString(format)

@TargetApi(Build.VERSION_CODES.M)
fun AppCompatActivity.requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        this.requestPermissions(permissions, requestCode)
}

@TargetApi(Build.VERSION_CODES.M)
fun AppCompatActivity.hasPermission(permission: String)
        = Build.VERSION.SDK_INT < Build.VERSION_CODES.M
            || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED