package org.aerogear.akow.dsl.base

import io.appium.java_client.AppiumDriver
import org.aerogear.akow.dsl.Screens

/**
 * Base DSL class definition for all application types.
 */
abstract class Application(final override var parent: Node, override var nodeName: String) : BaseNode(nodeName) {

    override val children = mutableListOf<Node>()

    lateinit var driver: AppiumDriver<*>
    internal lateinit var capabilities: DesiredCapabilities

    lateinit var screens: List<Node>

    /**
     * Path to application package.
     */
    var appPath: String? = null
    var appPackage: String? = null
    var appActivity: String? = null

    /**
     * All page object instances should be added.
     *
     * Example:
     * ```
     *  screens {
     *   +MainAndroidScreen()
     *   +TextFieldsAndroidScreen()
     *   ⋮
     *   ⋮
     * }
     * ```
     */
    fun screens(init: Screens.() -> Unit) = Screens().apply(init).also {
        it.parent = this@Application
        screens = it.children
    }

    override fun toString(): String {
        return "${this::class.simpleName}($appPackage)"
    }

    internal open val appiumDesiredCapabilities: org.openqa.selenium.remote.DesiredCapabilities
        get() {
            val desiredCapabilities = AppiumDesiredCapabilities()
            desiredCapabilities.setCapability("appActivity", appActivity)
            desiredCapabilities.setCapability("appPackage", appPackage)
            desiredCapabilities.setCapability("appPath", appPath)

            desiredCapabilities.merge(this.capabilities.appiumDesiredCapabilities)
            return desiredCapabilities
        }

}


