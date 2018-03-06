package org.aerogear.akow.dsl

import io.appium.java_client.remote.MobilePlatform
import org.aerogear.akow.dsl.base.AppiumDesiredCapabilities
import org.aerogear.akow.dsl.base.DesiredCapabilities


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
            caps.setCapability("appActivity", appActivity)
            caps.setCapability("deviceName", deviceName)
            caps.setCapability("language", language)
            caps.setCapability("locale", locale)
            caps.setCapability("noReset", noReset)
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

    //TODO add more capabilities

}
