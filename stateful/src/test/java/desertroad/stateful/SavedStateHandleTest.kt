package desertroad.stateful

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SavedStateHandleTest {

    @Test
    fun testViewModel() {
        launchFragment<FragmentWithViewModel>().run {
            onFragment {
                assertNull(it.vm.state)
                it.vm.state = 1780
                assertEquals(it.vm.state, 1780)
            }

            recreate()

            onFragment {
                assertEquals(it.vm.state, 1780)
            }
        }

        launchFragment<FragmentWithViewModel>().run {
            onFragment {
                assertNull(it.vm.namedState)
                it.vm.namedState = "world"
                assertEquals(it.vm.namedState, "world")
            }

            recreate()

            onFragment {
                assertEquals(it.vm.namedState, "world")
            }
        }
    }

    class FragmentWithViewModel : Fragment() {
        val vm: StatefulViewModel by viewModels()
    }

    class StatefulViewModel(state: SavedStateHandle) : ViewModel() {
        var state: Int? by state
        var namedState: String? by state.entry("entry")
    }
}