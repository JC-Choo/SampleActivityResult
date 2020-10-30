package dev.chu.activityresultsample2.month

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class MonthActivityContract : ActivityResultContract<Int, String>() {
    override fun createIntent(context: Context, input: Int?): Intent {
        return Intent(context, MonthActivity::class.java).apply {
            putExtra("input", input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        return when(resultCode) {
            Activity.RESULT_OK -> intent?.getStringExtra("result")
            else -> null
        }
    }

}