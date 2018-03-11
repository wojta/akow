package org.aerogear.akow.pageobject

import io.appium.java_client.AppiumDriver
import org.aerogear.akow.dsl.base.HasParent
import org.aerogear.akow.dsl.base.Node
import org.aerogear.akow.test.AkowTestContext
import org.aerogear.akow.tools.DeferredElement
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


typealias Accessor = ReadOnlyProperty<PageObject, DeferredElement>

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
     * Delegates to finding element by Id.
     */
    fun id(id: String): Accessor

    /**
     * Delegates to finding element by XPath. It's not recommended to use this, tell your development team to include id's to make tests less breakable.
     */
    fun xpath(path: String): Accessor

    /**
     * Delegates to finding element by multiple strategies. Next are used as a fallback in case that other fails.
     * Use with caution, this can make your test slow.
     */
    fun multiple(vararg strategies: Accessor) = object : Accessor {
        override fun getValue(thisRef: PageObject, property: KProperty<*>): DeferredElement {
            strategies.forEach {
                try {
                    val el = it.getValue(thisRef, property)
                    el.location
                    return el
                } catch (e: Exception) {
                }
            }
            throw NoSuchElementException("No strategy could find the element.\nmultipleStrategies")
        }
    }

    /**
     * Checks screen if exists. For use with [AkowTestContext.maybeOn]. Override it if you want to execute tests on the screen conditionally.
     */
    fun checkScreen() = true

}