package org.aerogear.akow.pageobject

import io.appium.java_client.MobileDriver
import io.appium.java_client.android.AndroidDriver
import org.aerogear.akow.dsl.Screens
import org.aerogear.akow.dsl.base.Application
import org.aerogear.akow.dsl.base.HasParent
import org.aerogear.akow.dsl.base.Node
import org.aerogear.akow.tools.DeferredElementFactory

/**
 * Created on 1/30/18.
 */
abstract class AndroidScreen : PageObject, HasParent {


    override var parent = Node.ROOT

    private val application by lazy {
        return@lazy (parent as Screens).parent as Application
    }

    override val driver
        get() = application.driver

    /**
     * Name of the node, currently only for debugging reasons.
     */
    override val nodeName: String
        get() = pageName

    internal val elementFactory = DeferredElementFactory()

    /**
     * Finds element by its [accessibilityId].
     */
    fun accessibilityId(accessibilityId: String) = lazy {
        elementFactory.create({
            return@create (driver as MobileDriver<*>).findElementByAccessibilityId(accessibilityId)
        }, "accesibilityId('$accessibilityId')")
    }

    /**
     * Finds element using UIAutomator [command].
     */
    fun uiAutomator(command: String) = lazy {
        elementFactory.create({
            return@create (driver as AndroidDriver<*>).findElementByAndroidUIAutomator(command)
        }, "uiAutomator('$command')")
    }

    override fun id(id: String) = lazy {
        elementFactory.create({
            return@create driver.findElementById(id)
        }, "id('$id')")
    }

    override fun xpath(path: String) = lazy {
        elementFactory.create({
            return@create driver.findElementByXPath(path)
        }, "xpath('$path')")
    }

    override fun requery(): AndroidScreen {
        elementFactory.requery()
        return this
    }
}