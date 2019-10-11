package wildcaravan.os

import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

/**
 * Associates the specified [value] with the specified [key] in the Bundle.
 */
fun <T> Bundle.put(key: String, value: T) {
    when (value) {
        null -> remove(key)

        is Boolean -> putBoolean(key, value)
        is BooleanArray -> putBooleanArray(key, value)
        is Double -> putDouble(key, value)
        is DoubleArray -> putDoubleArray(key, value)
        is Int -> putInt(key, value)
        is IntArray -> putIntArray(key, value)
        is Long -> putLong(key, value)
        is LongArray -> putLongArray(key, value)
        is String -> putString(key, value)

        is Byte -> putByte(key, value)
        is ByteArray -> putByteArray(key, value)
        is Char -> putChar(key, value)
        is CharArray -> putCharArray(key, value)
        is CharSequence -> putCharSequence(key, value)
        is Float -> putFloat(key, value)
        is FloatArray -> putFloatArray(key, value)
        is Short -> putShort(key, value)
        is ShortArray -> putShortArray(key, value)

        is Parcelable -> putParcelable(key, value)
        is Serializable -> putSerializable(key, value)

        else -> throw IllegalArgumentException("Unsupported type of value : ${(value as Any)::class.java}")
    }
}

/**
 * Returns the value corresponding to the given [key],
 * or `null` if such a key is not present in the Bundle.
 */
@Suppress("UNCHECKED_CAST", "EXTENSION_SHADOWED_BY_MEMBER")
fun <T> Bundle.get(key: String): T? = get(key) as T?