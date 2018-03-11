package org.aerogear.akow.dsl

import io.appium.java_client.remote.AndroidMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import io.appium.java_client.remote.MobilePlatform
import org.aerogear.akow.dsl.base.AppiumDesiredCapabilities
import org.aerogear.akow.dsl.base.DesiredCapabilities
import org.openqa.selenium.chrome.ChromeOptions


/**
 * Capabilities of device for testing Android app.
 */
class AndroidDesiredCapabilities : DesiredCapabilities(MobilePlatform.ANDROID) {

    /**
     * Retrieve Appium/Selenium specific [DesiredCapabilities].
     */
    override val appiumDesiredCapabilities: AppiumDesiredCapabilities
        get() {
            val caps = AppiumDesiredCapabilities()
            caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity)
            caps.setCapability("deviceName", deviceName)
            caps.setCapability("language", language)
            caps.setCapability("locale", locale)
            caps.setCapability(MobileCapabilityType.NO_RESET, noReset)
            if (chromeOptionsInitializer.isInitialized()) caps.setCapability(AndroidMobileCapabilityType.CHROME_OPTIONS, chromeOptions)
            return caps
        }


    override val nodeName: String = "androidDesiredCapabilities"

    /**
     * Starting activity for Android application.
     */
    var appActivity: String? = null

    /**
     * Name of the device, currently only default can be used.
     */
    override var deviceName = "Android Emulator"

    /**
     * Timeout in seconds used to wait for a device to become ready after booting
     */
    var deviceReadyTimeout: Int? = 30

    /**
     * Language to set for the simulator / emulator.
     */
    var language: String? = null

    /**
     *  Locale to set for the simulator / emulator.
     */
    var locale: String? = null

    /**
     * Don't reset app state before this session.
     */
    var noReset: Boolean? = null

    private val chromeOptionsInitializer = lazy {
        ChromeOptions()
    }

    /**
     * Chrome options
     * [see docs](https://sites.google.com/a/chromium.org/chromedriver/capabilities)
     */
    val chromeOptions: ChromeOptions by chromeOptionsInitializer
}
