package desertroad.stateful.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import desertroad.stateful.StateRegistry
import desertroad.stateful.Stateful

abstract class StatefulActivity : AppCompatActivity(), Stateful by StateRegistry() {

    protected open fun onPreRestoreStates() = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onPreRestoreStates()
        restoreStates(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveStates(outState)
    }
}