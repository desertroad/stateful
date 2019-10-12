package wildcaravan.stateful

import android.os.Bundle
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

class StateHolder : Stateful by StateRegistry() {
    val NUMBER_DEFAULT = 123
    var number: Int? by state { NUMBER_DEFAULT }
}

@RunWith(RobolectricTestRunner::class)
class StateRegistryTest {

    @Test(expected = IllegalStateException::class)
    fun `bad status`() {
        StateHolder().run {
            println(number)
        }
    }

    @Test
    fun `restore Int?`() {
        val states = StateHolder().run {

            restoreStates(null)
            assertEquals(number, NUMBER_DEFAULT)

            number = -7
            assertEquals(number, -7)

            Bundle().also(::saveStates)
        }

        println(states)

        StateHolder().run {
            restoreStates(states)
            assertEquals(number, -7)
        }
    }
}
