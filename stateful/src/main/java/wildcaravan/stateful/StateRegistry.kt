package wildcaravan.stateful

import android.os.Bundle
import wildcaravan.os.put

class StateRegistry : Stateful {
    private val states = mutableMapOf<String, State<*>>()

    @Suppress("UNCHECKED_CAST")
    private fun <T> State<T>.restore(value: Any?) = onRestore(value as T)

    override fun register(key: String, state: State<*>) {
        states[key] = state
    }

    override fun restoreStates(savedState: Bundle?) {
        if (savedState == null) {
            states.values.forEach { it.onInit() }
        } else {
            states.forEach { (key, state) -> state.restore(savedState.get(key)) }
        }
    }

    override fun saveStates(outState: Bundle) {
        states.forEach { (key, state) -> outState.put(key, state.onSave()) }
    }
}