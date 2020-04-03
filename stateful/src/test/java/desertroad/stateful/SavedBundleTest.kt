package desertroad.stateful

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragment
import androidx.test.core.app.launchActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class SavedBundleTest {

    @Test
    fun stateOnActivity() {
        launchActivity<ActivityWithState>().run {
            onActivity {
                assertNull(it.state)
                it.state = 123
                assertEquals(it.state, 123)
            }

            recreate()

            onActivity {
                assertEquals(it.state, 123)
            }
        }

        launchActivity<ActivityWithState>().run {
            onActivity {
                assertNull(it.namedState)
                it.namedState = -0.123
                assertEquals(it.namedState as Double?, -0.123)
            }

            recreate()

            onActivity {
                assertEquals(it.namedState, -0.123)
            }
        }
    }

    @Test
    fun stateOnFragment() {
        launchFragment<FragmentWithState>().run {
            onFragment {
                assertNull(it.state)
                it.state = "Hello"
                assertEquals(it.state, "Hello")
            }

            recreate()

            onFragment {
                assertEquals(it.state, "Hello")
            }
        }

        launchFragment<FragmentWithState>().run {
            onFragment {
                assertNull(it.namedState)
                it.namedState = -321L
                assertEquals(it.namedState as Long?, -321L)
            }

            recreate()

            onFragment {
                assertEquals(it.namedState, -321L)
            }
        }
    }

    class ActivityWithState : ComponentActivity() {
        var state: Int? by savedState()
        var namedState: Double? by savedState("test")
    }

    class FragmentWithState : Fragment() {
        var state: String? by savedState()
        var namedState: Long? by savedState("unit")
    }
}