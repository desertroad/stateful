package wildcaravan.stateful

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StateVar<T>(private val initializer: () -> T) : State<T>, ReadWriteProperty<Any?, T> {
    private var activated: Boolean = false
    private var _value: T? = null

    override fun onInit() {
        _value = initializer()
        activated = true
    }

    override fun onRestore(value: T) {
        check(!activated)

        _value = value
        activated = true
    }

    override fun onSave(): T {
        check(activated)

        activated = false

        @Suppress("UNCHECKED_CAST")
        return _value as T
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        check(activated) { "Unstable state before restoring or after saving" }

        @Suppress("UNCHECKED_CAST")
        return _value as T
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        check(activated) { "Unstable state before restoring or after saving" }
        _value = value
    }
}

class StateVarProvider<T>(private val parent: Stateful, private val initializer: () -> T) {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): StateVar<T> {
        val className = parent::class.java.name
        val key = "%s@%s".format(property.name, className)

        return StateVar(initializer).also { parent.register(key, it) }
    }
}

fun <T> Stateful.state(key: String, initializer: () -> T) =
    StateVar(initializer).also { register(key, it) }

fun <T> Stateful.state(initializer: () -> T) =
    StateVarProvider(this, initializer)