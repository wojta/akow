package org.aerogear.akow.dsl

import io.appium.java_client.remote.MobilePlatform
import org.aerogear.akow.dsl.base.AppiumDesiredCapabilities
import org.aerogear.akow.dsl.base.DesiredCapabilities

/**
 * Capabilities of device for testing iOS app.
 */
class IosDesiredCapabilities : DesiredCapabilities(MobilePlatform.IOS) {
    override val nodeName = "iosDesiredCapabilities"

    /**
     * Retrieve Appium/Selenium specific [DesiredCapabilities].
     */
    override val appiumDesiredCapabilities: AppiumDesiredCapabilities
        get() {
            val caps = AppiumDesiredCapabilities()
            return caps
        }

    //TODO implement this
}