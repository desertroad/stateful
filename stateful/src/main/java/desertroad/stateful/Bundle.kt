package desertroad.stateful

import android.os.Binder
import android.os.Bundle
import android.os.Parcelable
import android.util.Size
import android.util.SizeF
import java.io.Serializable

private fun onUnsupportedType(key: String, value: Any): Nothing {
    throw IllegalArgumentException("Unsupported type ${value.javaClass.name} for key \"$key\"")
}


internal fun Bundle.put(key: String, value: Any?) = when (value) {
    null -> remove(key)
    is Boolean -> putBoolean(key, value)
    is Byte -> putByte(key, value)
    is Char -> putChar(key, value)
    is Double -> putDouble(key, value)
    is Float -> putFloat(key, value)
    is Int -> putInt(key, value)
    is Long -> putLong(key, value)
    is Short -> putShort(key, value)
    is Bundle -> putBundle(key, value)
    is CharSequence -> putCharSequence(key, value)
    is Parcelable -> putParcelable(key, value)
    is BooleanArray -> putBooleanArray(key, value)
    is ByteArray -> putByteArray(key, value)
    is CharArray -> putCharArray(key, value)
    is DoubleArray -> putDoubleArray(key, value)
    is FloatArray -> putFloatArray(key, value)
    is IntArray -> putIntArray(key, value)
    is LongArray -> putLongArray(key, value)
    is ShortArray -> putShortArray(key, value)
    is Array<*> ->
        @Suppress("UNCHECKED_CAST")
        when (value::class) {
            Array<Parcelable>::class -> putParcelableArray(key, value as Array<Parcelable>)
            Array<String>::class -> putStringArray(key, value as Array<String>)
            Array<CharSequence>::class -> putCharSequenceArray(key, value as Array<CharSequence>)
            Array<Serializable>::class -> putSerializable(key, value)
            else -> onUnsupportedType(key, value)
        }
    is Serializable -> putSerializable(key, value)
    is Binder -> putBinder(key, value)
    is Size -> putSize(key, value)
    is SizeF -> putSizeF(key, value)
    else -> onUnsupportedType(key, value)
}