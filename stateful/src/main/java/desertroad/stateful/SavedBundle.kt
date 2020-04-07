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

fun SavedStateRegistryOwner.savedState(name: String) = savedState().Entry(name)

class SavedBundle(registry: SavedStateRegistry, key: String) {
    constructor(owner: SavedStateRegistryOwner, key: String) : this(owner.savedStateRegistry, key)

    private val state: Bundle by lazy {
        registry.registerSavedStateProvider(key, ::state)
        registry.consumeRestoredStateForKey(key) ?: Bundle()
    }

    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>) = Entry(property.name)

    inner class Entry(private val name: String) {
        @Suppress("UNCHECKED_CAST")
        operator fun <T> getValue(thisRef: Any?, property: KProperty<*>): T? =
            state.get(name) as T?

        operator fun <T> setValue(thisRef: Any?, property: KProperty<*>, value: T?) =
            state.put(name, value)
    }
}