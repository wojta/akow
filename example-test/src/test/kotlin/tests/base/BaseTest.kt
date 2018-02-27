package tests.base

import appium
import org.aerogear.akow.test.AkowTest
import org.junit.runners.Parameterized

/**
 * Created on 2/26/18.
 */
abstract class BaseTest : AkowTest(appium) {

    companion object {
        @Parameterized.Parameters(name = "{0}")
        @JvmStatic
        fun applications() = appium.applications.asList()
    }

}
