package desertroad.stateful

import android.os.Bundle

interface Stateful {
    fun register(key: String, state: State<*>)
    fun restoreStates(savedState: Bundle?)
    fun saveStates(outState: Bundle)
}