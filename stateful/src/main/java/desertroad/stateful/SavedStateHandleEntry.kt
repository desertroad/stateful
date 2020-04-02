package desertroad.stateful

import androidx.lifecycle.SavedStateHandle
import kotlin.reflect.KProperty

class SavedStateHandleEntry<T>(private val state: SavedStateHandle, private val name: String) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T? = state.get(name)

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) = state.set(name, value)
}