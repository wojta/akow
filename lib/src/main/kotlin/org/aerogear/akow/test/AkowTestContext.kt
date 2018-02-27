package org.aerogear.akow.test

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.MobilePlatform
import org.aerogear.akow.dsl.AndroidApplication
import org.aerogear.akow.dsl.Appium
import org.aerogear.akow.dsl.IosApplication
import org.aerogear.akow.dsl.base.Application
import org.aerogear.akow.exceptions.MultiplePageObjectsFound
import org.aerogear.akow.exceptions.NoPageObjectFound
import org.aerogear.akow.pageobject.AndroidScreen
import org.aerogear.akow.pageobject.PageObject
import org.openqa.selenium.WebElement
import java.net.URL
import kotlin.reflect.KClass

/**
 * Class provides basic context for Akow Test DSL.
 */
class AkowTestContext(val application: Application, internal val appium: Appium) {

    internal var platformDriverMap = mutableMapOf<String, AppiumDriver<*>>()
    var clsDriverMap = mutableMapOf<KClass<out AppiumDriver<*>>, AppiumDriver<*>>()

    /**
     * Provides context of testing on page objects.
     *
     * You have two levels of page objects:
     * 1. Interfaces extending [PageObject], where you write platform-independent page object implementation.
     * 2. Class implementing interface from 1 and extending platform specific screen (e.g. [AndroidScreen]) to have actual implementation of finding
     * [WebElement]s
     * and filling the
     * page object.
     *
     * Use only level 1 in [T], level 2 will be injected into context automatically. There must be one and only one implementation of level 1 interface per platform. Level 2 classes must be
     * instantiated in [Application.screens] section.
     *
     * @throws NoPageObjectFound
     * @throws MultiplePageObjectsFound
     */
    inline fun <reified T : PageObject> on(pageObjectContext: T.() -> Unit) {
        val pageObjects = application.screens.filterIsInstance<T>()
        when (pageObjects.count()) {
            0 -> throw NoPageObjectFound("No page object found for class ${T::class.simpleName}, make sure you added platform specific implementation to the <screens> section")
            1 -> { //there should be 1:1 between PageObject interfaces and their platform specific implementations
                pageObjects.first().pageObjectContext()
            }
            else -> throw MultiplePageObjectsFound("There are multiple page objects implementing ${T::class.simpleName}, make sure you added the platform specific implementation to the <screens> " +
                    "section just once")
        }
    }

    /**
     * Executes code in a context of [AppiumDriver], use this only for things you can't do with other DSL. If you use driver that's not same as current platform executed in the test, code will be
     * skipped.
     */
    inline fun <reified T : AppiumDriver<*>> driver(inDriver: T.() -> Unit) = if (clsDriverMap[T::class] == application.driver) (clsDriverMap[T::class] as T).inDriver() else null

    /**
     * Executes [platformSpecific] code only on platform with name [platformName]
     */
    fun platformOnly(platformName: String, platformSpecific: AkowTestContext.() -> Unit) {
        if (platformName == application.driver.platformName) platformSpecific.invoke(this)
    }

    /**
     *  Executes code in a context of [AndroidDriver], if you call this on other platform, code will be skipped.
     */
    fun androidDriver(inDriver: AndroidDriver<MobileElement>.() -> Unit) = driver(inDriver)

    /**
     * Executes [platformSpecific] code only on Android platform.
     */
    fun androidOnly(platformSpecific: AkowTestContext.() -> Unit) = platformOnly(MobilePlatform.ANDROID, platformSpecific)

    /**
     * Executes [platformSpecific] code only on iOS platform.
     */
    fun iosOnly(platformSpecific: AkowTestContext.() -> Unit) = platformOnly(MobilePlatform.IOS, platformSpecific)

    internal fun init() {
        appium.applications.children.filterIsInstance<Application>().forEach {

            when (it) {
                is AndroidApplication -> {
                    val driver = AndroidDriver<MobileElement>(URL(appium.serverPath), it.appiumDesiredCapabilities)
                    platformDriverMap[MobilePlatform.ANDROID] = driver
                    clsDriverMap[AndroidDriver::class] = driver
                    it.driver = driver
                }
                is IosApplication -> {
                    //TODO iOS
                }
                else -> throw UnsupportedOperationException("not implemented yet")
            }
        }
    }

    internal fun destroy() {
        platformDriverMap.values.forEach {
            it.quit()
        }
    }

}
