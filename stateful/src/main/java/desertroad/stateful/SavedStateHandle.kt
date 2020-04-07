package desertroad.stateful

import androidx.lifecycle.SavedStateHandle
import kotlin.reflect.KProperty

/**
 *
 */
fun SavedStateHandle.entry(key: String) = SavedStateHandleEntry(this, key)

/**
 *
 */
operator fun SavedStateHandle.provideDelegate(thisRef: Any?, property: KProperty<*>) =
    entry(property.name)

