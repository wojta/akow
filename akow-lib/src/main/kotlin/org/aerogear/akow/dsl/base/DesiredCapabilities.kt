package org.aerogear.akow.dsl.base

import io.appium.java_client.remote.MobilePlatform

typealias AppiumDesiredCapabilities = org.openqa.selenium.remote.DesiredCapabilities

/**
 * Base class for device's capabilities depending on [platformName] (e.g. [MobilePlatform.ANDROID]).
 * Check [https://appium.io/docs/en/writing-running-appium/caps/#appium-desired-capabilities]
 */
abstract class DesiredCapabilities(val platformName: String) : Node {

    /**
     * Device name, especially needed for iOS.
     */
    open var deviceName = ""

    /**
     * Retrieve Appium/Selenium specific [AppiumDesiredCapabilities].
     */
    internal abstract val appiumDesiredCapabilities: AppiumDesiredCapabilities

}
