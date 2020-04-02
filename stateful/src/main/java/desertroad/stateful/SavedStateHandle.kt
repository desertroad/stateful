package desertroad.stateful

import androidx.lifecycle.SavedStateHandle
import kotlin.reflect.KProperty

operator fun <T> SavedStateHandle.getValue(thisRef: Any?, property: KProperty<*>): T? =
    get<T>(property.name)

operator fun <T> SavedStateHandle.setValue(thisRef: Any?, property: KProperty<*>, value: T?) =
    set(property.name, value)

inline fun <reified T> SavedStateHandle.entry(key: String) = SavedStateHandleEntry<T>(this, key)