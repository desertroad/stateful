package wildcaravan.app

import android.app.Activity
import androidx.fragment.app.Fragment
import wildcaravan.os.get

fun <T> Activity.arg(key: String): T? = intent.extras?.get<T>(key)

fun <T> Fragment.arg(key: String): T? = arguments?.get<T>(key)

fun <T> android.app.Fragment.arg(key: String): T? = arguments?.get<T>(key)