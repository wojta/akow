package tests.old

import appium
import org.aerogear.akow.test.AkowTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertEquals

/**
 * Created on 2/21/18.
 */
@RunWith(Parameterized::class)
class FirstTest : AkowTest(appium) {

    companion object {
        @Parameterized.Parameters(name = "{0}")
        @JvmStatic
        fun applications() = appium.applications.asList()
    }

    @Test
    fun testPokus() {
        assertEquals(2, 2, "shit")
    }

}