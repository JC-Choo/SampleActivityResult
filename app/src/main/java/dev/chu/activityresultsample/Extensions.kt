package dev.chu.activityresultsample

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import dev.chu.activityresultsample.old_result.OldActivity

val Any.TAG: String
    get() = this::class.simpleName ?: this.toString()

fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, msg, duration).show()

fun Fragment.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(requireContext(), msg, duration).show()

fun Context.checkPermissions(vararg permissions: String): Boolean =
    permissions.all {
        ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }

fun AppCompatActivity.requestLocation(requestCode: Int) {
    val permission = Manifest.permission.ACCESS_FINE_LOCATION
    if (checkPermissions(permission)) {
        toast("Permission granted")
    } else {
        requestPermissions(arrayOf(permission), requestCode)
    }
}

fun Fragment.requestLocation(requestCode: Int) {
    val permission = Manifest.permission.ACCESS_FINE_LOCATION
    if (requireContext().checkPermissions(permission)) {
        toast("Permission granted")
    } else {
        requestPermissions(arrayOf(permission), requestCode)
    }
}

inline val Bundle.toString: String
    get() = keySet().map {
        it to get(it)
    }.joinToString()

/**
 * A container for an activity result as obtained form {@link Activity#onActivityResult}
 * onActivityResult 에서 얻어진 양식으로써 액티비티 결과에 대한 컨테이너이다.
 * 즉, onActivityResult 의 결과로 resultCode, intent 를 포함.
 */
inline val ActivityResult.toString: String
    get() = buildString {
        val resultStr = when (resultCode) {
            Activity.RESULT_OK -> "RESULT_OK"
            Activity.RESULT_CANCELED -> "RESULT_CANCELED"
            else -> "Result $resultCode"
        }
        append("ResultCode = $resultStr")

        val data = data?.extras?.toString.orEmpty()
        if (data.isNotEmpty()) {
            append("\nExtra = $data")
        }
    }