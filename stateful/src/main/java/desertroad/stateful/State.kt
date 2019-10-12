package desertroad.stateful

interface State<T> {
    fun onInit() = Unit
    fun onRestore(value: T)
    fun onSave(): T
}