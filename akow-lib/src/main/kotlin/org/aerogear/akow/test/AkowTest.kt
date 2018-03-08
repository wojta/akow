package org.aerogear.akow.test

import io.appium.java_client.AppiumDriver
import org.aerogear.akow.dsl.Appium
import org.aerogear.akow.dsl.base.Application
import org.junit.runners.Parameterized
import java.lang.IllegalArgumentException

/**
 * Make your tests inherit this class to use [akow] function to write tests using Akow DSL.
 */
abstract class AkowTest(val appium: Appium) {

    /**
     * AkowTest needs to be initialized by [Application] object passed as parameter to parametrized test.
     *
     * It's possible to do it like this:
     *
     * ```
     * @RunWith(Parameterized::class)
     * class FirstTest:AkowTest(appium) {
     *
     *   companion object {
     *       @Parameterized.Parameters(name = "{0}")
     *       @JvmStatic
     *       fun applications() = appium.applications.asList()
     *   }
     *  ⋮
     *  ⋮
     *}
     *```
     *
     */
    @Parameterized.Parameter
    lateinit var application: Application

    private val testContext: AkowTestContext
        get() = _testContext
                ?: throw UninitializedPropertyAccessException("Test context wasn't initialized")

    private var _testContext: AkowTestContext? = null

    /**
     * Target platform of current running test, e.g. "Android"
     */
    val currentPlatform: String
        get() = application.driver.platformName ?: "uknown"

    /**
     * Access to [AppiumDriver], use it in rare cases when you can't do something with using Akow DSL only.
     */
    lateinit var driver: AppiumDriver<*>
        private set


    /**
     * Access to Akow DSL context. This allows you to write tests using Akow DSL with page objects pattern.
     */
    fun akow(testContext: AkowTestContext.() -> Unit) {
        if (_testContext == null) {
            appiumInit()
        }
        testContext(this.testContext)
        appiumDestroy()

    }

    /**
     * Akow test initialization.
     */
    private fun appiumInit() {
        if (!::application.isInitialized) {
            throw IllegalArgumentException("You'll need to initialize your test with application object. Please use parametrized test and initialize it with your list of applications." +
                    "@RunWith(Parameterized::class)\n" +
                    "class YourTest : AkowTest(appium) {\n" +
                    "\n" +
                    "    companion object {\n" +
                    "        @Parameterized.Parameters(name = \"{0}\")\n" +
                    "        @JvmStatic\n" +
                    "        fun applications() = appium.applications.asList()\n" +
                    "    }" +
                    " ⋮" +
                    " ⋮" +
                    "}")
        }

        _testContext = AkowTestContext(application, appium).also(AkowTestContext::init)
    }

    /**
     * Akow test shutdown.
     */
    private fun appiumDestroy() {
        testContext.destroy()
        _testContext = null
    }

}