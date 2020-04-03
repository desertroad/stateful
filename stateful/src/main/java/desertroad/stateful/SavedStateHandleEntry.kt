package desertroad.stateful

import androidx.lifecycle.SavedStateHandle
import kotlin.reflect.KProperty

class SavedStateHandleEntry(private val state: SavedStateHandle, private val name: String) {
    operator fun <T> getValue(thisRef: Any?, property: KProperty<*>): T? = state.get(name)

    operator fun <T> setValue(thisRef: Any?, property: KProperty<*>, value: T?) =
        state.set(name, value)
}