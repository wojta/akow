package org.aerogear.akow.pageobject

import io.appium.java_client.AppiumDriver
import org.aerogear.akow.dsl.base.HasParent
import org.aerogear.akow.dsl.base.Node
import org.aerogear.akow.test.AkowTestContext
import org.aerogear.akow.tools.DeferredElement
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Interface for implementing platform-independent page objects.
 */
interface PageObject : Node, HasParent {

    val pageName: String
        get() = this::class.simpleName ?: "unknown"

    /**
     * Remote driver (e.g. [AppiumDriver]) associated with the page object. It's not recommended to use this directly. Use [AkowTestContext.driver] instead.
     */
    val driver: RemoteWebDriver

    /**
     * Deleg
     */
    fun id(id: String) = object : ReadOnlyProperty<PageObject, DeferredElement> {
        override fun getValue(thisRef: PageObject, property: KProperty<*>) = DeferredElement({ return@DeferredElement thisRef.driver.findElementById(id) })
    }


}