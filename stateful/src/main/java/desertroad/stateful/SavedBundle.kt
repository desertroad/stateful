package desertroad.stateful

import android.os.Bundle
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryOwner
import java.util.WeakHashMap
import kotlin.reflect.KProperty

private const val DEFAULT_SAVED_BUNDLE = "desertroad.stateful.DEFAULT_SAVED_BUNDLE"

private val defaultSavedBundles = WeakHashMap<SavedStateRegistryOwner, SavedBundle>()

private val SavedStateRegistryOwner.defaultSavedBundle: SavedBundle
    get() = defaultSavedBundles.getOrPut(this) { SavedBundle(this, DEFAULT_SAVED_BUNDLE) }

fun SavedStateRegistryOwner.savedState() = defaultSavedBundle

inline fun <reified T> SavedStateRegistryOwner.savedState(name: String) = savedState().Named<T>(name)

class SavedBundle(registry: SavedStateRegistry, key: String) :
    SavedStateRegistry.SavedStateProvider {
    constructor(owner: SavedStateRegistryOwner, key: String) : this(owner.savedStateRegistry, key)

    val state: Bundle by lazy {
        registry.registerSavedStateProvider(key, this)
        registry.consumeRestoredStateForKey(key) ?: Bundle()
    }

    override fun saveState(): Bundle = state

    @Suppress("UNCHECKED_CAST")
    operator fun <T> getValue(thisRef: Any?, property: KProperty<*>): T? =
        state.get(property.name) as T?

    operator fun <T> setValue(thisRef: Any?, property: KProperty<*>, value: T?) =
        state.put(property.name, value)

    inner class Named<T>(private val name: String) {
        @Suppress("UNCHECKED_CAST")
        operator fun getValue(thisRef: Any?, property: KProperty<*>): T? =
            state.get(name) as T?

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) =
            state.put(name, value)
    }
}