package org.aerogear.akow.pageobject

import io.appium.java_client.MobileDriver
import io.appium.java_client.android.AndroidDriver
import org.aerogear.akow.dsl.Screens
import org.aerogear.akow.dsl.base.Application
import org.aerogear.akow.dsl.base.HasParent
import org.aerogear.akow.dsl.base.Node
import org.aerogear.akow.tools.DeferredElement
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created on 1/30/18.
 */
abstract class AndroidScreen : PageObject, HasParent {


    override var parent = Node.ROOT

    private val application by lazy {
        return@lazy (parent as Screens).parent as Application
    }

    override val driver by lazy { return@lazy application.driver as AndroidDriver<*> }

    /**
     * Name of the node, currently only for debugging reasons.
     */
    override val nodeName: String
        get() = pageName

    fun accessibilityId(id: String) = object : ReadOnlyProperty<PageObject, DeferredElement> {
        override fun getValue(thisRef: PageObject, property: KProperty<*>) = DeferredElement({
            return@DeferredElement (thisRef.driver as MobileDriver<*>).findElementByAccessibilityId(id)
        })
    }

}