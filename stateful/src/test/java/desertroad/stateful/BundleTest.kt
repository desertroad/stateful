package desertroad.stateful

import android.os.Bundle
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class BundleTest {
    @Test
    fun putStringArray() {
        val bundle = Bundle()
        bundle.put("sa", arrayOf("hello", "world"))

        assertEquals(bundle.get("sa")?.javaClass, Array<String>::class.java)
    }

    @Test(expected = IllegalArgumentException::class)
    fun putNumberArray() {
        val bundle = Bundle()
        bundle.put("na", arrayOf<Number>(1, 3.14))
    }
}